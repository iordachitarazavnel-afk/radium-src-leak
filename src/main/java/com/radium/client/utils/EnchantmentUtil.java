package com.radium.client.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantments;

public class EnchantmentUtil {
    public static boolean hasEnchantment(ItemStack stack, Object enchantment) {
        return stack.hasEnchantments() && stack.getEnchantments().containsKey(enchantment);
    }
}
