package com.radium.client.events;

import java.lang.annotation.*;

/**
 * Annotare pentru metode care ascultă evenimente (Tick, Render3D, ChunkData etc.)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventListener { }
