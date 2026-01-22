package com.radium.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.block.BlockState;

import java.awt.Color;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.radium.client.modules.render.SuspiciousEsp;

public final class RenderUtils {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    private RenderUtils() {} // prevent instantiation

    // Render a filled box at given coordinates
    public static void renderFilledBox(MatrixStack matrices,
                                       float x1, float y1, float z1,
                                       float x2, float y2, float z2,
                                       Color color) {
        // Implementarea reală depinde de RenderSystem / VertexConsumer
        // Placeholder:
        // System.out.println("Render box: " + x1 + "," + y1 + "," + z1);
    }

    // Render a line between two points
    public static void renderLine(MatrixStack matrices, Color color, Vec3 start, Vec3 end) {
        // Placeholder: folosește RenderSystem / VertexConsumer
    }

    // Render all blocks from a cache
    public static void renderBlockCache(MatrixStack matrices,
                                        ConcurrentHashMap<Long, Set<BlockPos>> blockCache,
                                        Color color) {
        if (matrices == null || blockCache == null || color == null) return;

        blockCache.values().forEach(blockSet -> {
            for (BlockPos pos : blockSet) {
                if (pos != null) {
                    renderBox(matrices, pos, color.getRed() / 255f,
                            color.getGreen() / 255f, color.getBlue() / 255f,
                            color.getAlpha() / 255f);
                }
            }
        });
    }

    // Render a single block at position
    private static void renderBox(MatrixStack matrices, BlockPos pos, float r, float g, float b, float a) {
        // Placeholder: folosește metoda ta de bounding box sau cub OpenGL
        // ex: GlStateManager.pushMatrix(); GlStateManager.translate(...); drawCube(); GlStateManager.popMatrix();
    }

    // Returns camera position
    public static Vec3 getCameraPos() {
        if (mc.player != null) {
            return new Vec3(mc.player.getX(), mc.player.getEyeY(), mc.player.getZ());
        }
        return new Vec3(0, 0, 0);
    }

    // Returns Camera object
    public static Camera getCamera() {
        if (mc.gameRenderer != null) return mc.gameRenderer.getCamera();
        return null;
    }

    // Simple 3D vector class
    public static class Vec3 {
        public final double x, y, z;
        public Vec3(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
    }
}
