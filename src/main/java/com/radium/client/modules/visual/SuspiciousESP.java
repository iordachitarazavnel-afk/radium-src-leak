package com.radium.client.modules.visual;

import com.radium.client.client.RadiumClient;
import com.radium.client.gui.settings.BooleanSetting;
import com.radium.client.gui.settings.NumberSetting;
import com.radium.client.gui.settings.Setting;
import com.radium.client.modules.Module;
import com.radium.client.utils.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.math.Box;
import com.radium.client.events.event.EventListener;
import com.radium.client.events.event.TickEvent;
import com.radium.client.events.event.ChunkDataEvent;
import com.radium.client.events.event.Render3DEvent;

import java.util.ArrayList;

public class SuspiciousESP extends Module {

    private final BooleanSetting wanderingTraders = new BooleanSetting("Wandering Traders", true);
    private final BooleanSetting villagers = new BooleanSetting("Villagers", true);
    private final BooleanSetting llamas = new BooleanSetting("Llamas", true);
    private final BooleanSetting pillagers = new BooleanSetting("Pillagers", true);
    private final BooleanSetting deepslate = new BooleanSetting("Deepslate", true);
    private final BooleanSetting kelp = new BooleanSetting("Kelp", true);
    private final NumberSetting deepslateMinY = new NumberSetting("Deepslate Min Y", -64, 320, 8, 1);
    private final NumberSetting deepslateMaxY = new NumberSetting("Deepslate Max Y", -64, 320, 128, 1);
    private final NumberSetting alpha = new NumberSetting("Alpha", 1, 255, 100, 1);
    private final BooleanSetting tracers = new BooleanSetting("Tracers", false);

    public SuspiciousESP() {
        super("SuspiciousESP", "Highlights entities like villagers, traders, and mobs", Category.VISUAL);
        addSettings(wanderingTraders, villagers, llamas, pillagers, deepslate, kelp, deepslateMinY, deepslateMaxY, alpha, tracers);
    }

    @EventListener
    public void onTick(TickEvent event) {
        // aici poți adăuga logica de tick, dacă vrei să faci ceva periodic
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        // aici poți adăuga logica când chunk-urile se încarcă
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        for (Entity entity : new ArrayList<>(MinecraftClient.getInstance().world.getEntities())) {
            if ((wanderingTraders.getValue() && entity instanceof MerchantEntity) ||
                (villagers.getValue() && entity instanceof VillagerEntity) ||
                (llamas.getValue() && entity instanceof LlamaEntity) ||
                (pillagers.getValue() && entity instanceof PillagerEntity)) {

                Box box = entity.getBoundingBox();
              RenderUtil.drawBox(event.matrixStack, box, 1f, 0f, 0f, alpha.getValue().intValue() / 255f);
                if (tracers.getValue()) {
                    RenderUtil.drawTracer(event.matrixStack, MinecraftClient.getInstance().player.getPos(), entity.getPos(), alpha.getValue().intValue());
                }
            }
        }
    }
}
