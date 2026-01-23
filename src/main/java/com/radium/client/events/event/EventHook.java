package com.radium.client.events.event;

import com.radium.client.client.RadiumClient; // folosește clientul tău
import net.minecraft.entity.player.PlayerEntity;

public class EventHook {

    public static void onAttack(PlayerEntity target) {
        AttackEvent attackEvent = new AttackEvent(); // fără parametri
        RadiumClient.EVENT_BUS.fire(attackEvent); // folosește EventManager-ul din RadiumClient

        if (!attackEvent.isCancelled()) {
            // codul real de atac aici
        }
    }
}

