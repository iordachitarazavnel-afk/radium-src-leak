package com.radium.client.modules.visual;

import com.radium.client.events.EventListener;
import com.radium.client.events.event.ChunkDataEvent;
import com.radium.client.events.event.Render3DEvent;
import com.radium.client.events.event.TickEvent;
import com.radium.client.gui.settings.BooleanSetting;
import com.radium.client.gui.settings.NumberSetting;
import com.radium.client.modules.Module;
import com.radium.client.utils.render.RenderUtils;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class SuspiciousESP extends Module {

    // Entity settings
    private final BooleanSetting wanderingTraders = new BooleanSetting("Wandering Traders", true);
    private final BooleanSetting villagers = new BooleanSetting("Villagers", true);
    private final BooleanSetting llamas = new BooleanSetting("Llamas", true);
    private final BooleanSetting pillagers = new BooleanSetting("Pillagers", true);

    // Block settings
    private final BooleanSetting deepslate = new BooleanSetting("Deepslate", true);
    private final NumberSetting deepslateMinY = new NumberSetting("Deepslate Min Y", -64, -64, 320, 1);
    private final NumberSetting deepslateMaxY = new NumberSetting("Deepslate Max Y", 128, -64, 320, 1);
    private final BooleanSetting kelp = new BooleanSetting("Kelp", true);

    // Render settings
    private final NumberSetting alpha = new NumberSetting("Alpha", 100, 0, 255, 1);
    private final BooleanSetting tracers = new BooleanSetting("Tracers", false);

    public SuspiciousESP() {
        super("SuspiciousESP", "Highlights suspicious entities and blocks", Category.VISUAL);
        addSettings(
                wanderingTraders, villagers, llamas, pillagers,
                deepslate, deepslateMinY, deepslateMaxY, kelp,
                alpha, tracers
        );
    }

    @EventListener
    public void onTick(TickEvent event) {
        // Optional logic for updating entities or blocks each tick
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        // Can scan chunk for deepslate or kelp if needed
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        ClientPlayerEntity player = mc.player;

        if (player == null || mc.world == null) return;

        // Render entities
        for (Entity entity : mc.world.getEntities()) {
            if ((entity instanceof VillagerEntity && villagers.getBoolean()) ||
                (entity.getType().toString().contains("wandering_trader") && wanderingTraders.getBoolean()) ||
                (entity instanceof LlamaEntity && llamas.getBoolean()) ||
                (entity instanceof PillagerEntity && pillagers.getBoolean())) {

                Box box = entity.getBoundingBox();
                RenderUtils.drawBox(box, alpha.getNumber());

                if (tracers.getBoolean()) {
                    RenderUtils.drawTracer(player.getPos(), entity.getPos(), alpha.getNumber());
                }
            }
        }

        // Render blocks: Deepslate
        if (deepslate.getBoolean()) {
            mc.world.getBlockEntities().forEach((pos, blockEntity) -> {
                if (mc.world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE &&
                    pos.getY() >= deepslateMinY.getNumber() &&
                    pos.getY() <= deepslateMaxY.getNumber()) {

                    RenderUtils.drawBox(new Box(pos), alpha.getNumber());
                }
            });
        }

        // Render blocks: Kelp
        if (kelp.getBoolean()) {
            mc.world.getBlockEntities().forEach((pos, blockEntity) -> {
                if (mc.world.getBlockState(pos).getBlock() == Blocks.KELP) {
                    RenderUtils.drawBox(new Box(pos), alpha.getNumber());
                }
            });
        }
    }
}
