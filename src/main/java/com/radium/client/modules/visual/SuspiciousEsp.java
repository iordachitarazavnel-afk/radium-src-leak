package com.radium.client.modules.render;

import com.radium.client.events.event.EventListener;
import com.radium.client.events.event.events.ChunkDataEvent;
import com.radium.client.events.event.events.Render3DEvent;
import com.radium.client.events.event.events.TickEvent;
import com.radium.client.modules.Category;
import com.radium.client.modules.Module;
import com.radium.client.modules.setting.BooleanSetting;
import com.radium.client.modules.setting.NumberSetting;
import com.radium.client.modules.setting.Setting;
import com.radium.client.utils.BlockUtil;
import com.radium.client.utils.EncryptedString;
import com.radium.client.utils.RenderUtils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;

import java.awt.Color;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SuspiciousEsp extends Module {

    public static SuspiciousEsp instance;

    private final BooleanSetting wanderingTraders = new BooleanSetting(EncryptedString.of("Wandering Traders"), true);
    private final BooleanSetting villagers = new BooleanSetting(EncryptedString.of("Villagers"), true);
    private final BooleanSetting llamas = new BooleanSetting(EncryptedString.of("Llamas"), true);
    private final BooleanSetting pillagers = new BooleanSetting(EncryptedString.of("Pillagers"), true);
    private final BooleanSetting deepslate = new BooleanSetting(EncryptedString.of("Deepslate"), true);
    private final BooleanSetting kelp = new BooleanSetting(EncryptedString.of("Kelp"), true);
    private final NumberSetting deepslateMinY = new NumberSetting(EncryptedString.of("Deepslate Min Y"), -64, 320, 8, 1);
    private final NumberSetting deepslateMaxY = new NumberSetting(EncryptedString.of("Deepslate Max Y"), -64, 320, 128, 1);
    private final NumberSetting alpha = new NumberSetting(EncryptedString.of("Alpha"), 1, 255, 100, 1);
    private final BooleanSetting tracers = new BooleanSetting(EncryptedString.of("Tracers"), false);

    private final ConcurrentHashMap<Long, Set<BlockPos>> cachedDeepslate = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Set<BlockPos>> cachedKelp = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private final Set<Long> scanningChunks = ConcurrentHashMap.newKeySet();

    private volatile boolean needsRescan = false;
    private int tickCounter = 0;

    public SuspiciousEsp() {
        super(EncryptedString.of("Suspicious ESP"), EncryptedString.of("Highlights suspicious structures and entities"), -1, Category.RENDER);
        this.addSettings(new Setting[]{wanderingTraders, villagers, llamas, pillagers, deepslate, kelp, deepslateMinY, deepslateMaxY, alpha, tracers});
        instance = this;
    }

    public static SuspiciousEsp getInstance() {
        if (instance == null) instance = new SuspiciousEsp();
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        cachedDeepslate.clear();
        cachedKelp.clear();
        scanningChunks.clear();
        needsRescan = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        cachedDeepslate.clear();
        cachedKelp.clear();
        scanningChunks.clear();
    }

    @EventListener
    public void onTick(TickEvent event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null) {
            tickCounter++;
            if (needsRescan || tickCounter % 100 == 0) {
                needsRescan = false;
                scanAllChunks(mc.world);
            }
        }
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        if (event.getChunk() instanceof Chunk) scanChunk((Chunk) event.getChunk());
    }

    private void scanAllChunks(World world) {
        if (world != null) {
            for (Chunk chunk : BlockUtil.getLoadedChunks(world)) {
                scanChunk(chunk);
            }
        }
    }

    private void scanChunk(Chunk chunk) {
        if (chunk == null) return;
        long chunkKey = chunk.getPos().toLong();
        if (!scanningChunks.add(chunkKey)) return;

        executor.submit(() -> {
            try {
                Set<BlockPos> foundDeepslate = ConcurrentHashMap.newKeySet();
                Set<BlockPos> foundKelp = ConcurrentHashMap.newKeySet();

                int startX = chunk.getPos().getStartX();
                int startZ = chunk.getPos().getStartZ();
                ChunkSection[] sections = chunk.getSectionArray();
                int minSection = chunk.getWorld().getBottomSectionIndex();
                int minY = deepslateMinY.getIntValue();
                int maxY = deepslateMaxY.getIntValue();

                for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
                    ChunkSection section = sections[sectionIndex];
                    if (section == null || section.isEmpty()) continue;

                    int sectionY = (minSection + sectionIndex) * 16;
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            for (int y = 0; y < 16; y++) {
                                BlockState state = section.getBlockState(x, y, z);
                                BlockPos pos = new BlockPos(startX + x, sectionY + y, startZ + z);

                                if (deepslate.getValue() && pos.getY() >= minY && pos.getY() <= maxY && isRotatedDeepslate(state))
                                    foundDeepslate.add(pos);

                                if (kelp.getValue() && (state.getBlock() == Blocks.KELP || state.getBlock() == Blocks.KELP_PLANT) && isFullHeightKelp(chunk, pos))
                                    foundKelp.add(pos);
                            }
                        }
                    }
                }

                if (foundDeepslate.isEmpty()) cachedDeepslate.remove(chunkKey);
                else cachedDeepslate.put(chunkKey, foundDeepslate);

                if (foundKelp.isEmpty()) cachedKelp.remove(chunkKey);
                else cachedKelp.put(chunkKey, foundKelp);

            } finally {
                scanningChunks.remove(chunkKey);
            }
        });
    }

    private boolean isRotatedDeepslate(BlockState state) {
        // implementarea ta aici
        return state.getBlock() == Blocks.DEEPSLATE; // simplificat
    }

    private boolean isFullHeightKelp(Chunk chunk, BlockPos pos) {
        // simplificat, poți extinde
        return true;
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) return;

        Camera cam = RenderUtils.getCamera();
        if (cam == null) return;

        MatrixStack matrices = event.matrixStack;
        int alphaValue = alpha.getIntValue();

        // entities
        for (Entity entity : mc.world.getEntities()) {
            if (entity == null) continue;
            boolean shouldRender = false;
            Color entityColor = Color.WHITE;

            if (wanderingTraders.getValue() && entity instanceof WanderingTraderEntity) {
                shouldRender = true;
                entityColor = new Color(0, 255, 255, alphaValue);
            } else if (villagers.getValue() && entity instanceof VillagerEntity) {
                shouldRender = true;
                entityColor = new Color(0, 255, 0, alphaValue);
            } else if (llamas.getValue() && entity instanceof LlamaEntity) {
                shouldRender = true;
                entityColor = new Color(255, 255, 0, alphaValue);
            } else if (pillagers.getValue() && entity instanceof PillagerEntity) {
                shouldRender = true;
                entityColor = new Color(255, 0, 0, alphaValue);
            }

            if (shouldRender) {
                RenderUtils.renderFilledBox(matrices,
                        (float) entity.getX(), (float) entity.getY(), (float) entity.getZ(),
                        (float) entity.getX() + 1f, (float) entity.getY() + 2f, (float) entity.getZ() + 1f,
                        entityColor);
            }
        }

        // blocks
        if (deepslate.getValue()) RenderUtils.renderBlockCache(matrices, cachedDeepslate, new Color(128, 128, 128, alphaValue));
        if (kelp.getValue()) RenderUtils.renderBlockCache(matrices, cachedKelp, new Color(0, 200, 100, alphaValue));
    }
}
