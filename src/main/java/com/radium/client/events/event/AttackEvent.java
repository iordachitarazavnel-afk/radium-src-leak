package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import com.radium.client.events.Listener;

import net.minecraft.entity.player.PlayerEntity;
import java.util.ArrayList;

public class AttackEvent extends CancellableEvent<AttackEvent.AttackListener> {

    public final PlayerEntity target;

    public AttackEvent(PlayerEntity target) {
        this.target = target;
    }

    @Override
    public void fire(ArrayList<AttackListener> listeners) {
        for (AttackListener l : listeners) {
            l.onAttack(this);
        }
    }

    @Override
    public Class<AttackListener> getListenerType() {
        return AttackListener.class;
    }

    public interface AttackListener extends Listener {
        void onAttack(AttackEvent event);
    }
}
