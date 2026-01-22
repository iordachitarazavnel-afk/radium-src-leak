package com.radium.client.events.event;

import com.radium.client.events.Event;
import com.radium.client.events.Listener;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class Render3DEvent extends Event<Render3DEvent.Render3DListener> {

    public final MatrixStack matrixStack;

    public Render3DEvent(MatrixStack matrixStack) {
        this.matrixStack = matrixStack;
    }

    @Override
    public void fire(ArrayList<Render3DListener> listeners) {
        for (Render3DListener listener : listeners) listener.onRender3D(this);
    }

    @Override
    public Class<Render3DListener> getListenerType() {
        return Render3DListener.class;
    }

    public interface Render3DListener extends Listener {
        void onRender3D(Render3DEvent event);
    }
}
