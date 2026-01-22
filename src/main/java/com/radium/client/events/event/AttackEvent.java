package com.radium.client.events.event;

import net.minecraft.entity.Entity;

public class AttackEvent {
    private final Entity target;

    public AttackEvent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }
}
