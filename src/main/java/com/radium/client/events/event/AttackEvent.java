package com.radium.client.events.event.events;

import com.radium.client.events.Event;
import com.radium.client.events.Listener;

import net.minecraft.entity.Entity;

import java.util.ArrayList;

public class AttackEvent extends Event<AttackEvent.AttackListener> {

    private final Entity target;

    public AttackEvent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public interface AttackListener extends Listener {
        void onAttack(AttackEvent event);
    }

    @Override
    public void fire(ArrayList<AttackListener> listeners) {
        for (AttackListener listener : listeners) {
            listener.onAttack(this);
        }
    }

    @Override
    public Class<AttackListener> getListenerType() {
        return AttackListener.class;
    }
}
