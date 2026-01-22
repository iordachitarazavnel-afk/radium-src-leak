package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.world.chunk.Chunk;

public class ChunkDataEvent extends CancellableEvent<ChunkDataEvent.ChunkListener> {

    private final Chunk chunk;

    public ChunkDataEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }

    @Override
    public void fire(java.util.ArrayList<ChunkListener> listeners) {
        for (ChunkListener listener : listeners) {
            listener.onChunkData(this);
        }
    }

    @Override
    public Class<ChunkListener> getListenerType() {
        return ChunkListener.class;
    }

    public interface ChunkListener extends com.radium.client.events.Listener {
        void onChunkData(ChunkDataEvent event);
    }
}
