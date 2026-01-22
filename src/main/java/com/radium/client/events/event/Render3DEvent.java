package com.radium.client.events.event;

import net.minecraft.class_4587; // MatrixStack
import java.util.ArrayList;

public class Render3DEvent {
    public final class_4587 matrixStack;

    public Render3DEvent(class_4587 matrixStack) {
        this.matrixStack = matrixStack;
    }

    public interface Render3DListener {
        void onRender3D(Render3DEvent event);
    }

    public void fire(ArrayList<Render3DListener> listeners) {
        for (Render3DListener listener : listeners) {
            listener.onRender3D(this);
        }
    }
}
