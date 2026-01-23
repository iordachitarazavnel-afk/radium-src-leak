package com.radium.client.utils;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class RenderUtil {

    public static void drawBox(MatrixStack matrices, Box box, int alpha) {
        VertexConsumer vc = Tessellator.getInstance()
                .getBuffer();

        WorldRenderer.drawBox(
                matrices,
                vc,
                box,
                1f, 0f, 0f,
                alpha / 255f
        );
    }

    public static void drawTracer(MatrixStack matrices, Vec3d from, Vec3d to, int alpha) {
        VertexConsumer vc = Tessellator.getInstance().getBuffer();

        BufferBuilder bb = (BufferBuilder) vc;
        bb.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        bb.vertex(matrices.peek().getPositionMatrix(), (float) from.x, (float) from.y, (float) from.z)
                .color(0f, 1f, 0f, alpha / 255f).next();
        bb.vertex(matrices.peek().getPositionMatrix(), (float) to.x, (float) to.y, (float) to.z)
                .color(0f, 1f, 0f, alpha / 255f).next();

        BufferRenderer.drawWithGlobalProgram(bb.end());
    }
}
