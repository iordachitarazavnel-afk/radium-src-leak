package com.radium.client.events.event;

import com.radium.client.client.RadiumClient;
import com.radium.client.events.CancellableEvent;
import com.radium.client.events.Listener;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.world.chunk.WorldChunk;

import java.util.ArrayList;

public class ChunkDataEvent extends CancellableEvent<ChunkDataEvent.ChunkListener> {

    private final ChunkDataS2CPacket packet;

    public ChunkDataEvent(ChunkDataS2CPacket packet) {
        this.packet = packet;
    }

    public ChunkDataS2CPacket getPacket() {
        return packet;
    }

    public WorldChunk getChunk() {
        if (RadiumClient.mc.world == null) return null;
        return RadiumClient.mc.world.getChunk(packet.getChunkX(), packet.getChunkZ());
    }

    /* ================= EVENT SYSTEM ================= */

    @Override
    public void fire(ArrayList<ChunkListener> listeners) {
        for (ChunkListener listener : listeners) {
            listener.onChunkData(this);
        }
    }

    @Override
    public Class<ChunkListener> getListenerType() {
        return ChunkListener.class;
    }

    public interface ChunkListener extends Listener {
        void onChunkData(ChunkDataEvent event);
    }
}
