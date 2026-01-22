package com.radium.client.modules.visual;

import com.radium.client.client.RadiumClient;
import com.radium.client.events.EventListener;
import com.radium.client.events.event.ChunkDataEvent;
import com.radium.client.events.event.Render3DEvent;
import com.radium.client.events.event.TickEvent;
import com.radium.client.gui.settings.BooleanSetting;
import com.radium.client.gui.settings.NumberSetting;
import com.radium.client.gui.settings.Setting;
import com.radium.client.modules.Module;
import com.radium.client.utils.render.RenderUtils;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.WanderingTraderEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.VillagerEntity;

import java.util.ArrayList;

public class SuspiciousESP extends Module {

    private final BooleanSetting wanderingTraders = new BooleanSetting("Wandering Traders", true);
    private final BooleanSetting villagers = new BooleanSetting("Villagers", true);
    private final BooleanSetting llamas = new BooleanSetting("Llamas", true);
    private final BooleanSetting pillagers = new BooleanSetting("Pillagers", true);
    private final BooleanSetting deepslate = new BooleanSetting("Deepslate", true);
    private final BooleanSetting kelp = new BooleanSetting("Kelp", true);
    private final BooleanSetting tracers = new BooleanSetting("Tracers", false);

    private final NumberSetting deepslateMinY = new NumberSetting("Deepslate Min Y", -64, 320, 8, 1);
    private final NumberSetting deepslateMaxY = new NumberSetting("Deepslate Max Y", -64, 320, 128, 1);
    private final NumberSetting alpha = new NumberSetting("Alpha", 1, 255, 100, 1);

    public SuspiciousESP() {
        super("SuspiciousESP", "Highlights entities and blocks like deepslate, mobs, etc.", Category.VISUAL);
        addSettings(
                wanderingTraders, villagers, llamas, pillagers,
                deepslate, kelp, tracers,
                deepslateMinY, deepslateMaxY, alpha
        );
    }

    @EventListener
    public void onTick(TickEvent event) {
        // aici poți pune logica care trebuie să ruleze la fiecare tick
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        // logica când chunk-urile se încarcă
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        MatrixStack stack = event.matrixStack;

        // Exemplu: highlight entities
        for (Entity entity : new ArrayList<>(mc.world.getEntities())) {
            if ((wanderingTraders.getValue() && entity instanceof WanderingTraderEntity) ||
                (villagers.getValue() && entity instanceof VillagerEntity) ||
                (llamas.getValue() && entity instanceof LlamaEntity) ||
                (pillagers.getValue() && entity instanceof PillagerEntity)) {

                Box box = entity.getBoundingBox();
                RenderUtils.drawBox(stack, box, alpha.getValue().intValue());
                if (tracers.getValue()) {
                    RenderUtils.drawTracer(stack, mc.player.getPos(), entity.getPos(), alpha.getValue().intValue());
                }
            }
        }

        // TODO: poți adăuga highlight pentru deepslate și kelp aici
    }
}
