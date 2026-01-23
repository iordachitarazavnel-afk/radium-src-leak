package com.radium.client.utils;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.VertexConsumerProvider;

public class RenderUtil {

    public static void drawBox(MatrixStack matrixStack, Box box, float alpha) {
        // placeholder simplu
        WorldRenderer.drawBox(matrixStack, null, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, 1f, 0f, 0f, alpha);
    }

    public static void drawTracer(MatrixStack matrixStack, net.minecraft.util.math.Vec3d from, net.minecraft.util.math.Vec3d to, float alpha) {
        // placeholder simplu
        WorldRenderer.drawLine(matrixStack, from, to, 0f, 1f, 0f, alpha);
    }
}
