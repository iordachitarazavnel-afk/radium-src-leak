package com.radium.client.events.event;

import com.radium.client.client.RadiumClient;
import com.radium.client.events.CancellableEvent;
import com.radium.client.events.Listener;
import net.minecraft.world.chunk.Chunk; // Clasa Chunk reală
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket; // Packet real pentru chunk

import java.util.ArrayList;

public class ChunkDataEvent extends CancellableEvent<ChunkDataEvent.ChunkDataListener> {

    public ChunkDataS2CPacket packet; // packet-ul real

    public ChunkDataEvent(ChunkDataS2CPacket packet) {
        this.packet = packet;
    }

    // Obține chunk-ul din world
    public Chunk getChunk() {
        if (RadiumClient.mc == null || RadiumClient.mc.world == null) return null;
        int chunkX = packet.getX(); // metoda reală a packetului
        int chunkZ = packet.getZ();
        return RadiumClient.mc.world.getChunk(chunkX, chunkZ);
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

    public interface ChunkDataListener extends Listener {
        void onChunkData(ChunkDataEvent event);
    }
}
