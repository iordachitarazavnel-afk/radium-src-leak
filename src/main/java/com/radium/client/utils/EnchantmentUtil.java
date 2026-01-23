package com.radium.client.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchantmentUtil {

    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment) {
        return EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        return EnchantmentHelper.getLevel(enchantment, stack);
    }
}
