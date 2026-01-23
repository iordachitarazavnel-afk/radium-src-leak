package com.radium.client.events.event;

import com.radium.client.events.CancellableEvent;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

public class Render3DEvent extends CancellableEvent {

    private final MatrixStack matrixStack;
    private final Matrix4f matrix4f;
    private final float tickDelta;

    public Render3DEvent(MatrixStack matrixStack, Matrix4f matrix4f, float tickDelta) {
        this.matrixStack = matrixStack;
        this.matrix4f = matrix4f;
        this.tickDelta = tickDelta;
    }

    public MatrixStack getMatrixStack() {
        return matrixStack;
    }

    public Matrix4f getMatrix4f() {
        return matrix4f;
    }

    public float getTickDelta() {
        return tickDelta;
    }
}
