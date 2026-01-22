package com.radium.client.events.event;

import net.minecraft.client.util.math.MatrixStack;

public class Render3DEvent {
    private final MatrixStack matrices;
    private final float tickDelta;

    public Render3DEvent(MatrixStack matrices, float tickDelta) {
        this.matrices = matrices;
        this.tickDelta = tickDelta;
    }

    public MatrixStack getMatrices() {
        return matrices;
    }

    public float getTickDelta() {
        return tickDelta;
    }
}
