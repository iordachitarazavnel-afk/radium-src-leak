package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import com.radium.client.events.Listener;

public class AttackEvent extends CancellableEvent<AttackEvent.AttackListener> {

    @Override
    public void fire(java.util.ArrayList<AttackListener> listeners) {
        for (AttackListener listener : listeners) {
            listener.onAttack(this);
            if (isCancelled()) break;
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
