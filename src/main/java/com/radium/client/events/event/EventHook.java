package com.radium.client.events.event;

import com.radium.client.Radium;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Aici NU e Event propriu-zis,
 * e clasa unde Minecraft hooks → Radium events
 */
public class EventHook {

    /* ===================== */
    /*        TICK           */
    /* ===================== */
    public static void onTick() {
        TickEvent event = new TickEvent();
        Radium.EVENT_BUS.post(event);
    }

    /* ===================== */
    /*        ATTACK         */
    /* ===================== */
    public static void onAttack(Entity target) {
        if (!(target instanceof PlayerEntity player)) return;

        AttackEvent event = new AttackEvent(player);
        Radium.EVENT_BUS.post(event);
    }

    /* ===================== */
    /*       RENDER 3D       */
    /* ===================== */
    public static void onRender3D(MatrixStack matrices, float tickDelta) {
        Render3DEvent event = new Render3DEvent(matrices, tickDelta);
        Radium.EVENT_BUS.post(event);
    }
}
