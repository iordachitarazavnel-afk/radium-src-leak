package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;

public class TickEvent extends CancellableEvent<TickEvent.TickListener> {

    @Override
    public void fire(java.util.ArrayList<TickListener> listeners) {
        for (TickListener listener : listeners) {
            listener.onTick(this);
        }
    }

    @Override
    public Class<TickListener> getListenerType() {
        return TickListener.class;
    }

    public interface TickListener extends com.radium.client.events.Listener {
        void onTick(TickEvent event);
    }
}
