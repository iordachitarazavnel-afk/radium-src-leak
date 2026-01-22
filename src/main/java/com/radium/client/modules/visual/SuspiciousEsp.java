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
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

public class SuspiciousESP extends Module {

    // Settings
    private final BooleanSetting wanderingTraders = new BooleanSetting("Wandering Traders", true);
    private final BooleanSetting villagers = new BooleanSetting("Villagers", true);
    private final BooleanSetting llamas = new BooleanSetting("Llamas", true);
    private final BooleanSetting pillagers = new BooleanSetting("Pillagers", true);
    private final BooleanSetting deepslate = new BooleanSetting("Deepslate", true);
    private final BooleanSetting kelp = new BooleanSetting("Kelp", true);
    private final NumberSetting deepslateMinY = new NumberSetting("Deepslate Min Y", -64, -64, 320, 1);
    private final NumberSetting deepslateMaxY = new NumberSetting("Deepslate Max Y", 128, -64, 320, 1);
    private final NumberSetting alpha = new NumberSetting("Alpha", 100, 0, 255, 1);
    private final BooleanSetting tracers = new BooleanSetting("Tracers", false);

    public SuspiciousESP() {
        super("SuspiciousESP", "Highlights entities and blocks", Category.VISUAL);
        addSettings(
                wanderingTraders, villagers, llamas, pillagers,
                deepslate, kelp, deepslateMinY, deepslateMaxY, alpha, tracers
        );
    }

    @EventListener
    public void onTick(TickEvent event) {
        // Logic for updating entities every tick (if needed)
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        // Example: Scan chunk for deepslate if setting enabled
        if (deepslate.getBoolean()) {
            event.getChunk().getBlockEntities().forEach((pos, blockEntity) -> {
                // Placeholder logic
            });
        }
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        // Render highlighted blocks/entities
        mc.world.getEntities().forEach(entity -> {
            if (entity.getType().toString().contains("villager") && villagers.getBoolean()) {
                RenderUtils.drawBox(entity.getBoundingBox(), alpha.getNumber());
            }
            if (entity.getType().toString().contains("llama") && llamas.getBoolean()) {
                RenderUtils.drawBox(entity.getBoundingBox(), alpha.getNumber());
            }
            // Add other entity types similarly
        });

        if (deepslate.getBoolean()) {
            // Render deepslate blocks in loaded chunks
            mc.world.getBlockEntities().forEach((pos, blockEntity) -> {
                if (mc.world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE &&
                        pos.getY() >= deepslateMinY.getNumber() &&
                        pos.getY() <= deepslateMaxY.getNumber()) {
                    RenderUtils.drawBox(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), alpha.getNumber());
                }
            });
        }
    }
}
