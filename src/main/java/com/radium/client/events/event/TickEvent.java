package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;

public class TickEvent extends CancellableEvent {

    @Override
    public Class<?> getListenerType() {
        return TickListener.class;
    }

    public interface TickListener {
        void onTick(TickEvent event);
    }
}
