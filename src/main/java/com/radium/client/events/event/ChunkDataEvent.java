package com.radium.client.events.event;

import com.radium.client.events.Event;
import com.radium.client.events.Listener;
import net.minecraft.class_2680;
import net.minecraft.class_2818;

import java.util.ArrayList;

public class ChunkDataEvent extends Event<ChunkDataEvent.ChunkDataListener> {
    private final class_2818 chunk;

    public ChunkDataEvent(class_2818 chunk) {
        this.chunk = chunk;
    }

    public class_2818 getChunk() {
        return chunk;
    }

    public interface ChunkDataListener extends Listener {
        void onChunkData(ChunkDataEvent event);
    }

    @Override
    public void fire(ArrayList<ChunkDataListener> listeners) {
        for (ChunkDataListener listener : listeners) {
            listener.onChunkData(this);
        }
    }

    @Override
    public Class<ChunkDataListener> getListenerType() {
        return ChunkDataListener.class;
    }
}

