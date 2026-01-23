package com.radium.client.utils;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class RenderUtil {

    public static void drawBox(MatrixStack matrixStack, Box box, float r, float g, float b, float a) {
        VertexConsumerProvider.Immediate buffer = VertexConsumerProvider.immediate();
        WorldRenderer.drawBox(matrixStack, buffer, box, r, g, b, a);
        buffer.draw();
    }

    public static void drawTracer(MatrixStack matrixStack, Vec3d from, Vec3d to, float r, float g, float b, float a) {
        VertexConsumerProvider.Immediate buffer = VertexConsumerProvider.immediate();
        WorldRenderer.drawLine(matrixStack, buffer, from, to, r, g, b, a);
        buffer.draw();
    }
}
