package com.radium.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

import java.awt.Color;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.radium.client.modules.render.SuspiciousEsp;

public final class RenderUtils {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    private RenderUtils() {} // prevent instantiation

    // Renderizează block cache pentru SuspiciousESP
    public static void renderBlockCache(MatrixStack matrices,
                                        ConcurrentHashMap<Long, Set<BlockPos>> blockCache,
                                        Color color) {
        if (matrices == null || blockCache == null || color == null) return;

        for (Set<BlockPos> blockSet : blockCache.values()) {
            if (blockSet == null) continue;

            for (BlockPos pos : blockSet) {
                if (pos == null) continue;

                float r = color.getRed() / 255f;
                float g = color.getGreen() / 255f;
                float b = color.getBlue() / 255f;
                float a = color.getAlpha() / 255f;

                renderBox(matrices, pos, r, g, b, a);

                // Tracers către player
                if (SuspiciousEsp.getInstance().tracers.getValue() && mc.player != null) {
                    renderLine(matrices,
                            new Color(color.getRed(), color.getGreen(), color.getBlue(), 255),
                            mc.player.getPos(),
                            pos.toCenterPos());
                }
            }
        }
    }

    // Placeholder pentru cub (bounding box)
    private static void renderBox(MatrixStack matrices, BlockPos pos, float r, float g, float b, float a) {
        // Aici pui codul de rendering OpenGL/RenderSystem
    }

    // Placeholder pentru linie
    private static void renderLine(MatrixStack matrices, Color color, net.minecraft.util.math.Vec3d start, BlockPos end) {
        // Aici pui codul de rendering linie
    }

    // Camera position
    public static net.minecraft.util.math.Vec3d getCameraPos() {
        if (mc.player != null) {
            return mc.player.getCameraPosVec(1.0F);
        }
        return new net.minecraft.util.math.Vec3d(0, 0, 0);
    }

    public static net.minecraft.client.render.Camera getCamera() {
        if (mc.gameRenderer != null) {
            return mc.gameRenderer.getCamera();
        }
        return null;
    }
}
