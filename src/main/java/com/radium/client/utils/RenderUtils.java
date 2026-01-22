package com.radium.client.utils;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import com.mojang.blaze3d.systems.RenderSystem;

public class RenderUtils {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    /**
     * Desenează un box în lumea 3D
     * @param matrixStack MatrixStack de la Render3DEvent
     * @param box Box-ul de desenat
     * @param alpha Opacitatea (0-255)
     */
    public static void drawBox(MatrixStack matrixStack, Box box, int alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        RenderSystem.lineWidth(1.0F);
        // Folosim WorldRenderer pentru a desena box-ul
        WorldRenderer.drawBox(matrixStack, box, 1f, 0f, 0f, alpha / 255f); // roșu ca exemplu
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    /**
     * Desenează o linie (tracer) între două puncte
     * @param matrixStack MatrixStack de la Render3DEvent
     * @param from Start (de obicei player.getPos())
     * @param to End (entitate)
     * @param alpha Opacitate (0-255)
     */
    public static void drawTracer(MatrixStack matrixStack, Vec3d from, Vec3d to, int alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        RenderSystem.lineWidth(1.0F);
        // Linie simplă între două puncte
        WorldRenderer.drawLine(matrixStack, from, to, 0f, 1f, 0f, alpha / 255f); // verde ca exemplu
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }
}
