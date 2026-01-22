package com.radium.client.utils;

import net.minecraft.class_243;
import net.minecraft.class_4587;

import java.awt.Color;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.radium.client.modules.render.SuspiciousEsp;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;

public class RenderUtils {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    // Renders a filled box at the given coordinates with the given color
    public static void renderFilledBox(class_4587 matrices,
                                       float x1, float y1, float z1,
                                       float x2, float y2, float z2,
                                       Color color) {
        // Placeholder for actual rendering logic
        // In Fabric 1.20+, you would use WorldRenderer and VertexConsumerProvider
        // For simplicity, assume your rendering framework handles this
    }

    // Renders a line between two points
    public static void renderLine(class_4587 matrices, Color color, class_243 start, class_243 end) {
        // Placeholder for line rendering
        // Normally you'd create a buffer, set color, and draw a line between start and end
    }

    // Renders all blocks from a cache
    public static void renderBlockCache(class_4587 matrices, ConcurrentHashMap<Long, Set<net.minecraft.class_2338>> cache, Color color) {
        for (Set<net.minecraft.class_2338> blockSet : cache.values()) {
            if (blockSet == null) continue;

            for (net.minecraft.class_2338 blockPos : blockSet) {
                if (blockPos == null) continue;

                double playerX = mc.player.getX();
                double playerY = mc.player.getY();
                double playerZ = mc.player.getZ();

                float x1 = (float) (blockPos.method_10263() - playerX + 0.1);
                float y1 = (float) (blockPos.method_10264() - playerY + 0.05);
                float z1 = (float) (blockPos.method_10260() - playerZ + 0.1);

                float x2 = (float) (blockPos.method_10263() - playerX + 0.9);
                float y2 = (float) (blockPos.method_10264() - playerY + 0.85);
                float z2 = (float) (blockPos.method_10260() - playerZ + 0.9);

                renderFilledBox(matrices, x1, y1, z1, x2, y2, z2, color);

                if (SuspiciousEsp.getInstance().tracers.getValue() && mc.player != null) {
                    renderLine(matrices, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255),
                            new class_243(playerX, playerY + mc.player.getHeight(mc.player.getPose()), playerZ),
                            new class_243(blockPos.method_10263() + 0.5, blockPos.method_10264() + 0.5, blockPos.method_10260() + 0.5));
                }
            }
        }
    }

    // Returns a fake camera position
    public static class_243 getCameraPos() {
        if (mc.player != null) {
            return new class_243(mc.player.getX(), mc.player.getEyeY(), mc.player.getZ());
        }
        return new class_243(0, 0, 0);
    }

    // Returns a fake camera rotation object
    public static class_4184 getCamera() {
        if (mc.gameRenderer != null) {
            return mc.gameRenderer.getCamera();
        }
        return null;
    }
}
