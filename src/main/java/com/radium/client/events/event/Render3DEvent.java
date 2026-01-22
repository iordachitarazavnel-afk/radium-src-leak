package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.client.util.math.MatrixStack;

public class Render3DEvent extends CancellableEvent<Render3DEvent.Render3DListener> {

    public final MatrixStack matrixStack;

    public Render3DEvent(MatrixStack matrixStack) {
        this.matrixStack = matrixStack;
    }

    @Override
    public void fire(java.util.ArrayList<Render3DListener> listeners) {
        for (Render3DListener listener : listeners) {
            listener.onRender3D(this);
        }
    }

    @Override
    public Class<Render3DListener> getListenerType() {
        return Render3DListener.class;
    }

    public interface Render3DListener extends com.radium.client.events.Listener {
        void onRender3D(Render3DEvent event);
    }
}
