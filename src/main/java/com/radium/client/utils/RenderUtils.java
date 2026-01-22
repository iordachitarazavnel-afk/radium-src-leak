package com.radium.client.utils;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.Color;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class RenderUtils {

    private RenderUtils() {} // prevent instantiation

    public static Camera getCamera() {
        return net.minecraft.client.MinecraftClient.getInstance().gameRenderer.getCamera();
    }

    public static BlockPos getCameraPos() {
        return new BlockPos(net.minecraft.client.MinecraftClient.getInstance().player.getX(),
                            net.minecraft.client.MinecraftClient.getInstance().player.getEyeY(),
                            net.minecraft.client.MinecraftClient.getInstance().player.getZ());
    }

    public static void renderFilledBox(MatrixStack matrices,
                                       float x1, float y1, float z1,
                                       float x2, float y2, float z2,
                                       Color color) {
        // Aici va fi codul tău OpenGL sau WorldRenderer pentru cub
    }

    public static void renderBlockCache(MatrixStack matrices,
                                        ConcurrentHashMap<Long, Set<BlockPos>> blockCache,
                                        Color color) {
        for (Set<BlockPos> blocks : blockCache.values()) {
            for (BlockPos pos : blocks) {
                renderFilledBox(matrices, pos.getX(), pos.getY(), pos.getZ(),
                                pos.getX() + 1f, pos.getY() + 1f, pos.getZ() + 1f, color);
            }
        }
    }
}
