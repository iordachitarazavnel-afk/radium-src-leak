package com.radium.client.modules.visual;

import com.radium.client.events.EventListener;
import com.radium.client.events.event.ChunkDataEvent;
import com.radium.client.events.event.Render3DEvent;
import com.radium.client.events.event.TickEvent;
import com.radium.client.gui.settings.BooleanSetting;
import com.radium.client.gui.settings.NumberSetting;
import com.radium.client.modules.Module;
import com.radium.client.utils.render.RenderUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.util.math.Box;

public class SuspiciousESP extends Module {

    private final BooleanSetting wanderingTraders = new BooleanSetting("Wandering Traders", true);
    private final BooleanSetting villagers = new BooleanSetting("Villagers", true);
    private final BooleanSetting llamas = new BooleanSetting("Llamas", true);
    private final BooleanSetting pillagers = new BooleanSetting("Pillagers", true);

    private final NumberSetting alpha = new NumberSetting("Alpha", 100, 0, 255, 1);
    private final BooleanSetting tracers = new BooleanSetting("Tracers", false);

    public SuspiciousESP() {
        super("SuspiciousESP", "Highlights suspicious entities", Category.VISUAL);
        addSettings(wanderingTraders, villagers, llamas, pillagers, alpha, tracers);
    }

    @EventListener
    public void onTick(TickEvent event) {
        // Cod care trebuie rulat la fiecare tick
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        // Cod care procesează chunk-ul când e generat
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        ClientPlayerEntity player = mc.player;
        if (player == null || mc.world == null) return;

        for (Entity entity : mc.world.getEntities()) {
            if ((entity instanceof VillagerEntity && villagers.getValue()) ||
                (entity instanceof LlamaEntity && llamas.getValue()) ||
                (entity instanceof PillagerEntity && pillagers.getValue())) {

                Box box = entity.getBoundingBox();
                RenderUtils.drawBox(box, alpha.getValue());

                if (tracers.getValue()) {
                    RenderUtils.drawTracer(player.getPos(), entity.getPos(), alpha.getValue());
                }
            }
        }
    }
}
