package com.radium.client.events.event;

import java.util.ArrayList;

public class ChunkDataEvent extends Event<ChunkDataEvent.ChunkListener> {

    @Override
    public void fire(ArrayList<ChunkListener> listeners) {
        for (ChunkListener listener : listeners) listener.onChunkData(this);
    }

    @Override
    public Class<ChunkListener> getListenerType() {
        return ChunkListener.class;
    }

    public interface ChunkListener extends Listener {
        void onChunkData(ChunkDataEvent event);
    }
}

