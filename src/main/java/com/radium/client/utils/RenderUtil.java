package com.radium.client.utils;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class RenderUtil {

    public static void drawBox(MatrixStack matrices, Box box, float r, float g, float b, float alpha) {
        // aici trebuie să folosești WorldRenderer sau metodele tale de render
        // exemplu minimal
    }

    public static void drawTracer(MatrixStack matrices, Vec3d from, Vec3d to, float alpha) {
        // exemplu minimal, folosește liniile OpenGL sau metoda ta de tracer
    }
}
