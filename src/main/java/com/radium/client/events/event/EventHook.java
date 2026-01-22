package com.radium.client.events.event;

import com.radium.client.client.ModuleManager;
import com.radium.client.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class EventHook {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void onTick(ModuleManager moduleManager) {
        TickEvent tickEvent = new TickEvent();
        for (Module module : moduleManager.getModules()) {
            if (module.isEnabled()) {
                try {
                    module.onTick();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void onAttack(ModuleManager moduleManager, PlayerEntity target) {
        AttackEvent attackEvent = new AttackEvent(target);
        for (Module module : moduleManager.getModules()) {
            if (module.isEnabled()) {
                try {
                    // MaceSwap reaction
                    if (module.getName().equalsIgnoreCase("Mace Swap")) {
                        ((com.radium.client.modules.combat.MaceSwap) module).onAttack(attackEvent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
