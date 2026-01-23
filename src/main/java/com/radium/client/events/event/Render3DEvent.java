package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.client.util.math.MatrixStack;

public class Render3DEvent extends CancellableEvent {

    private final MatrixStack matrixStack;
    private final float tickDelta;

    public Render3DEvent(MatrixStack matrixStack, float tickDelta) {
        this.matrixStack = matrixStack;
        this.tickDelta = tickDelta;
    }

    public MatrixStack getMatrixStack() {
        return matrixStack;
    }

    public float getTickDelta() {
        return tickDelta;
    }

    @Override
    public Class<?> getListenerType() {
        return Render3DListener.class;
    }

    public interface Render3DListener {
        void onRender3D(Render3DEvent event);
    }
}
