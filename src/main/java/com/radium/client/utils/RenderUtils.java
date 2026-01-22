package com.radium.client.utils;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;

public class RenderUtils {

    // Desenează un cub
    public static void drawBox(DrawContext context, int x1, int y1, int z1, int x2, int y2, int z2, int alpha) {
        // Aici trebuie să adaugi codul tău OpenGL sau Minecraft Renderer pentru cub
        // Placeholder:
        // System.out.println("Drawing box from (" + x1+","+y1+","+z1+") to ("+x2+","+y2+","+z2+") with alpha " + alpha);
    }

    // Desenează un tracer
    public static void drawTracer(DrawContext context, Vec3d from, Vec3d to, int alpha) {
        // Placeholder pentru linia de tracer
        // System.out.println("Drawing tracer from " + from + " to " + to + " alpha: " + alpha);
    }
}
