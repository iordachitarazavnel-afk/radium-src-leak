package com.radium.client.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class EnchantmentUtil {

    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment) {
        return EnchantmentHelper.getEnchantments(stack).containsKey(enchantment);
    }

    public static int getLevel(ItemStack stack, Enchantment enchantment) {
        return EnchantmentHelper.getLevel(enchantment, stack);
    }

    public static Map<Enchantment, Integer> getEnchantments(ItemStack stack) {
        return EnchantmentHelper.getEnchantments(stack);
    }
}
