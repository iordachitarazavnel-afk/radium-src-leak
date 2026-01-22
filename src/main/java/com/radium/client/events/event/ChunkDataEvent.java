package com.radium.client.events.event.events;

import net.minecraft.class_2818; // WorldChunk

public class ChunkDataEvent {

    private final class_2818 chunk;

    public ChunkDataEvent(class_2818 chunk) {
        this.chunk = chunk;
    }

    public class_2818 getChunk() {
        return chunk;
    }
}
