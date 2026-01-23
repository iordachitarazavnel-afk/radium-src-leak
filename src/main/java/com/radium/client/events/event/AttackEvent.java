package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;

public class AttackEvent extends CancellableEvent<AttackEvent.AttackListener> {

    public AttackEvent() {}

    @Override
    public Class<AttackListener> getListenerType() {
        return AttackListener.class;
    }

    public interface AttackListener extends Listener {
        void onAttack(AttackEvent event);
    }
}
