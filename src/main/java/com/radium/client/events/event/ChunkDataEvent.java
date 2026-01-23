package com.radium.client.events.event;

import com.radium.client.client.RadiumClient;
import com.radium.client.events.CancellableEvent;
import com.radium.client.events.Listener;
import net.minecraft.class_2672; // PacketChunkData
import net.minecraft.class_2791; // Chunk

import java.util.ArrayList;

public class ChunkDataEvent extends CancellableEvent<ChunkDataEvent.ChunkDataListener> {

    public class_2672 packet;

    public ChunkDataEvent(class_2672 packet) {
        this.packet = packet;
    }

    // Obține chunk-ul din world
    public class_2791 getChunk() {
        if (RadiumClient.mc == null || RadiumClient.mc.field_1687 == null) return null;
        return RadiumClient.mc.field_1687.method_8497(this.packet.method_11523(), this.packet.method_11524());
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

    // Interfața listener pentru acest event
    public interface ChunkDataListener extends Listener {
        void onChunkData(ChunkDataEvent event);
    }
}
