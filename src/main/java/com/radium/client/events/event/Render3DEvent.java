package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.client.render.MatrixStack;
import org.joml.Matrix4f;

public class Render3DEvent extends CancellableEvent<Render3DEvent.Render3DListener> {

    public final MatrixStack matrixStack;
    public final Matrix4f matrix4f;
    public final float tickDelta;

    public Render3DEvent(MatrixStack matrixStack, Matrix4f matrix4f, float tickDelta) {
        this.matrixStack = matrixStack;
        this.matrix4f = matrix4f;
        this.tickDelta = tickDelta;
    }

    @Override
    public Class<Render3DListener> getListenerType() {
        return Render3DListener.class;
    }

    public interface Render3DListener extends Listener {
        void onRender3D(Render3DEvent event);
    }
}
