package com.radium.client.events.event;

import com.radium.client.RadiumClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;

/**
 * EventHook se ocupă cu trimiterea evenimentelor din Minecraft către EventBus-ul RadiumClient.
 * În acest exemplu, trimite un AttackEvent când jucătorul atacă o entitate.
 */
public class EventHook {

    /**
     * Apelat când playerul atacă o entitate.
     * @param target entitatea atacată
     */
    public static void onAttack(PlayerEntity target) {
        // Creăm un eveniment AttackEvent
        AttackEvent attackEvent = new AttackEvent();

        // Poți adăuga aici orice informație dacă vrei să extinzi AttackEvent
        // ex: attackEvent.target = target;

        // Trimitem evenimentul către EventBus-ul RadiumClient
        RadiumClient.EVENT_BUS.fire(attackEvent);

        // Dacă evenimentul a fost anulat, împiedică atacul efectiv
        if (attackEvent.isCancelled()) {
            // Blocăm atacul: depinde de contextul tău cum faci asta
            ClientPlayerEntity player = RadiumClient.mc.player;
            if (player != null) {
                player.swingHand(player.getActiveHand()); // exemplu simplu
            }
        }
    }
}
