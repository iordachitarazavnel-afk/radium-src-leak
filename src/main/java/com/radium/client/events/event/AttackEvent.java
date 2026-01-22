package com.radium.client.events.event;

import com.radium.client.events.Event;
import com.radium.client.events.Listener;
import net.minecraft.world.entity.LivingEntity; // Yarn mapping corect
import java.util.ArrayList;

public class AttackEvent extends Event<AttackEvent.AttackListener> {
    private final LivingEntity target;

    public AttackEvent(LivingEntity target) {
        this.target = target;
    }

    public LivingEntity getTarget() {
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
