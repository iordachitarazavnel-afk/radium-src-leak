package com.radium.client.modules.visual;

import com.radium.client.client.RadiumClient;
import com.radium.client.gui.settings.BooleanSetting;
import com.radium.client.gui.settings.NumberSetting;
import com.radium.client.gui.settings.Setting;
import com.radium.client.modules.Module;
import com.radium.client.utils.EncryptedString;
import com.radium.client.utils.RenderUtils;
import com.radium.client.events.event.EventListener;
import com.radium.client.events.event.TickEvent;
import com.radium.client.events.event.ChunkDataEvent;
import com.radium.client.events.event.Render3DEvent;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WanderingTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class SuspiciousESP extends Module {

    private final BooleanSetting wanderingTraders = new BooleanSetting(new EncryptedString("Wandering Traders"), true);
    private final BooleanSetting villagers = new BooleanSetting(new EncryptedString("Villagers"), true);
    private final NumberSetting alpha = new NumberSetting(new EncryptedString("Alpha"), 1, 255, 100, 1);

    public SuspiciousESP() {
        super("SuspiciousESP", "Highlights certain entities in the world", Category.VISUAL);
        addSettings(wanderingTraders, villagers, alpha);
    }

    @EventListener
    public void onTick(TickEvent event) {
        // tick logic, optional
    }

    @EventListener
    public void onChunkData(ChunkDataEvent event) {
        // chunk data logic, optional
    }

    @EventListener
    public void onRender3D(Render3DEvent event) {
        DrawContext context = event.getDrawContext(); // folosește DrawContext din Render3DEvent
        for (Entity entity : new ArrayList<>(MinecraftClient.getInstance().world.getEntities())) {
            if ((wanderingTraders.getValue() && entity instanceof WanderingTraderEntity) ||
                (villagers.getValue() && entity instanceof VillagerEntity)) {

                Box box = entity.getBoundingBox();
                RenderUtils.drawBox(context, (int) box.minX, (int) box.minY, (int) box.minZ,
                        (int) box.maxX, (int) box.maxY, (int) box.maxZ, alpha.getValue().intValue());

                RenderUtils.drawTracer(context,
                        MinecraftClient.getInstance().player.getPos(),
                        entity.getPos(),
                        alpha.getValue().intValue());
            }
        }
    }
}
