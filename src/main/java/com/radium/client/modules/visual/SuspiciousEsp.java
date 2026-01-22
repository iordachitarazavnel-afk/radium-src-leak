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

import java.awt.Color;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.minecraft.class_1297;
import net.minecraft.class_1501;
import net.minecraft.class_1604;
import net.minecraft.class_1646;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_3989;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_2246;
import net.minecraft.class_2391;
import net.minecraft.class_2393;

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

    private final ConcurrentHashMap<Long, Set<class_2338>> cachedDeepslate = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Set<class_2338>> cachedKelp = new ConcurrentHashMap<>();
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
        if (mc.field_1687 != null) {
            tickCounter++;
            if (needsRescan || tickCounter % 100 == 0) {
                needsRescan = false;
                scanAllChunks();
            }
        }
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        if (event.getChunk() instanceof class_2818) scanChunk((class_2818) event.getChunk());
    }

    private void scanAllChunks() {
        if (mc.field_1687 != null) {
            for (class_2818 chunk : BlockUtil.getLoadedChunks().toList()) {
                scanChunk(chunk);
            }
        }
    }

    private void scanChunk(class_2818 chunk) {
        if (chunk == null) return;
        long chunkKey = chunk.method_12004().method_8324();
        if (!scanningChunks.add(chunkKey)) return;

        executor.submit(() -> {
            try {
                Set<class_2338> foundDeepslate = ConcurrentHashMap.newKeySet();
                Set<class_2338> foundKelp = ConcurrentHashMap.newKeySet();

                class_1923 chunkPos = chunk.method_12004();
                int startX = chunkPos.method_8326();
                int startZ = chunkPos.method_8328();
                class_2826[] sections = chunk.method_12006();
                int minSection = mc.field_1687.method_32891();
                int minY = deepslateMinY.getIntValue();
                int maxY = deepslateMaxY.getIntValue();

                for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
                    class_2826 section = sections[sectionIndex];
                    if (section == null || section.method_38292()) continue;

                    int sectionY = (minSection + sectionIndex) * 16;
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            for (int y = 0; y < 16; y++) {
                                class_2680 state = section.method_12254(x, y, z);
                                class_2338 pos = new class_2338(startX + x, sectionY + y, startZ + z);

                                if (deepslate.getValue() && pos.method_10264() >= minY && pos.method_10264() <= maxY && isRotatedDeepslate(state))
                                    foundDeepslate.add(pos);

                                if (kelp.getValue() && (state.method_26204() instanceof class_2393 || state.method_26204() instanceof class_2391) && isFullHeightKelp(chunk, pos))
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

    private boolean isRotatedDeepslate(class_2680 state) {
        if (state.method_26204() == class_2246.field_28888) {
            try {
                if (state.method_28501().stream().anyMatch(prop -> prop.method_11899().equals("axis"))) {
                    String axis = ((class_2351) state.method_11654(class_2741.field_12496)).toString();
                    return !axis.equals("y");
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    private boolean isFullHeightKelp(class_2818 chunk, class_2338 startPos) {
        class_2339 pos = startPos.method_25503();
        for (int i = 0; i < 64; i++) {
            pos.method_10100(0, 1, 0);
            class_2680 aboveState = chunk.method_8320(pos);
            if (aboveState.method_26215()) {
                class_2680 belowAir = chunk.method_8320(pos.method_10074());
                return belowAir.method_26204() == class_2246.field_10382;
            }
            if (!(aboveState.method_26204() instanceof class_2393) && !(aboveState.method_26204() instanceof class_2391)) {
                if (aboveState.method_26204() == class_2246.field_10382) return true;
                return false;
            }
        }
        return false;
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        if (mc.field_1687 == null) return;
        class_4184 cam = RenderUtils.getCamera();
        if (cam == null) return;

        class_243 camPos = RenderUtils.getCameraPos();
        class_4587 matrices = event.matrixStack;

        int alphaValue = alpha.getIntValue();

        // entities
        if (wanderingTraders.getValue() || villagers.getValue() || llamas.getValue() || pillagers.getValue()) {
            for (class_1297 entity : mc.field_1687.method_18112()) {
                if (entity == null) continue;
                boolean shouldRender = false;
                Color entityColor = Color.WHITE;

                if (wanderingTraders.getValue() && entity instanceof class_3989) {
                    shouldRender = true;
                    entityColor = new Color(0, 255, 255, alphaValue);
                } else if (villagers.getValue() && entity instanceof class_1646) {
                    shouldRender = true;
                    entityColor = new Color(0, 255, 0, alphaValue);
                } else if (llamas.getValue() && entity instanceof class_1501) {
                    shouldRender = true;
                    entityColor = new Color(255, 255, 0, alphaValue);
                } else if (pillagers.getValue() && entity instanceof class_1604) {
                    shouldRender = true;
                    entityColor = new Color(255, 0, 0, alphaValue);
                }

                if (shouldRender) {
                    RenderUtils.renderFilledBox(matrices, (float) entity.method_23317(), (float) entity.method_23318(), (float) entity.method_23321(),
                            (float) entity.method_23317() + 1f, (float) entity.method_23318() + 2f, (float) entity.method_23321() + 1f, entityColor);
                }
            }
        }

        // blocks
        if (deepslate.getValue()) RenderUtils.renderBlockCache(matrices, cachedDeepslate, new Color(128,128,128, alphaValue));
        if (kelp.getValue()) RenderUtils.renderBlockCache(matrices, cachedKelp, new Color(0,200,100, alphaValue));
    }
}
