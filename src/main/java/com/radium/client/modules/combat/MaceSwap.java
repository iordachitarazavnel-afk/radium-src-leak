package com.radium.client.modules.combat;

import com.radium.client.modules.Module;
import com.radium.client.modules.Module.Category;
import com.radium.client.utils.EnchantmentUtil;
import com.radium.client.utils.EncryptedString;
import com.radium.client.utils.InventoryUtil;
import com.radium.client.events.event.AttackEvent;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public final class MaceSwap extends Module {
    private boolean isSwitching;
    private int previousSlot;

    public MaceSwap() {
        super("Mace Swap", "Switches to a mace when attacking", Category.COMBAT);
        System.out.println("[Radium] MaceSwap loaded!");
    }

    public void onAttack(AttackEvent event) {
        if (!isValidWeapon()) return;

        if (previousSlot == -1) previousSlot = mc.player.getInventory().selectedSlot;

        boolean swapped = InventoryUtil.swapItem(item -> item instanceof SwordItem || item instanceof AxeItem);
        if (swapped) isSwitching = true;
    }

    private boolean isValidWeapon() {
        Item item = mc.player.getMainHandStack().getItem();
        return item instanceof SwordItem || item instanceof AxeItem;
    }

    @Override
    public void onTick() {
        if (isSwitching) performSwitchBack();
    }

    private void performSwitchBack() {
        InventoryUtil.swap(previousSlot);
        resetState();
    }

    private void resetState() {
        previousSlot = -1;
        isSwitching = false;
    }
}
