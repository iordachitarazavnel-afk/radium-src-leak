package com.radium.client.utils;

import net.minecraft.class_2338;
import net.minecraft.class_4587;

import java.awt.Color;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class RenderUtils {

    private RenderUtils() {} // prevent instantiation

    /**
     * Renderizează block cache pentru SuspiciousESP
     *
     * @param matrices matricele de render (MatrixStack)
     * @param blockCache cache-ul de blockuri, key = chunk long, value = set de block positions
     * @param color culoarea pentru blockuri
     */
    public static void renderBlockCache(class_4587 matrices,
                                        ConcurrentHashMap<Long, Set<class_2338>> blockCache,
                                        Color color) {
        if (matrices == null || blockCache == null || color == null) return;

        blockCache.values().forEach(blockSet -> {
            for (class_2338 pos : blockSet) {
                if (pos != null) {
                    float r = color.getRed() / 255f;
                    float g = color.getGreen() / 255f;
                    float b = color.getBlue() / 255f;
                    float a = color.getAlpha() / 255f;

                    // Aici poți folosi metoda ta de render (cub, bounding box etc.)
                    // Exemplu simplificat (trebuie să implementezi cubul sau bounding box-ul)
                    RenderUtils.renderBox(matrices, pos, r, g, b, a);
                }
            }
        });
    }

    /**
     * Metodă helper pentru a desena un cub la poziția block-ului
     * Trebuie să înlocuiești cu implementarea ta de bounding box
     */
    private static void renderBox(class_4587 matrices, class_2338 pos, float r, float g, float b, float a) {
        // codul tău de OpenGL/RenderSystem aici
        // de exemplu:
        // GlStateManager.pushMatrix();
        // GlStateManager.translate(pos.method_10263(), pos.method_10264(), pos.method_10260());
        // drawCube(r, g, b, a);
        // GlStateManager.popMatrix();
    }
}
