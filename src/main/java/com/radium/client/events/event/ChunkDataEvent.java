package com.radium.client.events.event;

import com.radium.client.Radium;
import com.radium.client.events.CancellableEvent;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.world.chunk.WorldChunk;

public class ChunkDataEvent extends CancellableEvent {

    private final ChunkDataS2CPacket packet;

    public ChunkDataEvent(ChunkDataS2CPacket packet) {
        this.packet = packet;
    }

    public ChunkDataS2CPacket getPacket() {
        return packet;
    }

    public WorldChunk getChunk() {
        if (Radium.mc.world == null) return null;

        return Radium.mc.world.getChunk(
                packet.getChunkX(),
                packet.getChunkZ()
        );
    }
}
