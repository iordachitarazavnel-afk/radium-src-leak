package com.radium.client.events.event;

import com.radium.client.RadiumClient;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.world.chunk.Chunk;

public class ChunkDataEvent extends CancellableEvent<ChunkDataEvent.ChunkDataListener> {

    private final ChunkDataS2CPacket packet;

    public ChunkDataEvent(ChunkDataS2CPacket packet) {
        this.packet = packet;
    }

    public Chunk getChunk() {
        if (RadiumClient.mc.world == null) return null;
        int chunkX = packet.getX(); // sau packet.getChunkX() în funcție de mappings
        int chunkZ = packet.getZ(); // sau packet.getChunkZ()
        return RadiumClient.mc.world.getChunk(chunkX, chunkZ);
    }

    @Override
    public Class<ChunkDataListener> getListenerType() {
        return ChunkDataListener.class;
    }

    public interface ChunkDataListener extends Listener {
        void onChunkData(ChunkDataEvent event);
    }
}
