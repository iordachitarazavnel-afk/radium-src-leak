package com.radium.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;

public class RenderUtil {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    /**
     * Desenează un box în lume (wireframe)
     */
    public static void drawBox(MatrixStack matrixStack, Box box, int r, int g, int b, int alpha) {
        VertexConsumerProvider.Immediate provider = mc.getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer vertexConsumer = provider.getBuffer(RenderLayer.getLines());

        WorldRenderer.drawBox(matrixStack, vertexConsumer,
                box.minX, box.minY, box.minZ,
                box.maxX, box.maxY, box.maxZ,
                r / 255f, g / 255f, b / 255f, alpha / 255f);

        provider.draw();
    }

    /**
     * Desenează o linie (tracer) între două puncte
     */
    public static void drawTracer(MatrixStack matrixStack, Vec3d from, Vec3d to, int r, int g, int b, int alpha) {
        VertexConsumerProvider.Immediate provider = mc.getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer vertexConsumer = provider.getBuffer(RenderLayer.getLines());

        WorldRenderer.drawLine(matrixStack, vertexConsumer,
                from.x, from.y, from.z,
                to.x, to.y, to.z,
                r / 255f, g / 255f, b / 255f, alpha / 255f);

        provider.draw();
    }
}
