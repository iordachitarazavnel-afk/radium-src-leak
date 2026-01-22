package com.radium.client.events.event;

import com.radium.client.events.Event;
import com.radium.client.events.Listener;

import java.util.ArrayList;

public class TickEvent extends Event<TickEvent.TickListener> {

    @Override
    public void fire(ArrayList<TickListener> listeners) {
        for (TickListener listener : listeners) listener.onTick(this);
    }

    @Override
    public Class<TickListener> getListenerType() {
        return TickListener.class;
    }

    public interface TickListener extends Listener {
        void onTick(TickEvent event);
    }
}
