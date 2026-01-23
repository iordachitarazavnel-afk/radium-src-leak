package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.world.chunk.WorldChunk;

public class ChunkDataEvent extends CancellableEvent {

    private final ChunkDataS2CPacket packet;

    public ChunkDataEvent(ChunkDataS2CPacket packet) {
        this.packet = packet;
    }

    public WorldChunk getChunk() {
        if (MinecraftClient.getInstance().world == null) return null;

        return MinecraftClient.getInstance().world.getChunk(
                packet.getChunkX(),
                packet.getChunkZ()
        );
    }
}
