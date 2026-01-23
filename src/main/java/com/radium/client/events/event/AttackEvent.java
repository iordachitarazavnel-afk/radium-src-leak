package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.entity.player.PlayerEntity;

public class AttackEvent extends CancellableEvent {

    private final PlayerEntity target;

    public AttackEvent(PlayerEntity target) {
        this.target = target;
    }

    public PlayerEntity getTarget() {
        return target;
    }

    @Override
    public Class<?> getListenerType() {
        return AttackListener.class;
    }

    public interface AttackListener {
        void onAttack(AttackEvent event);
    }
}
