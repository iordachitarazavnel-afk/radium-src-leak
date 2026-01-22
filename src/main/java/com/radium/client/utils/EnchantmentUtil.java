package com.radium.client.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.Map;

public class EnchantmentUtil {

    /** Verifică dacă un ItemStack are un anumit enchantment */
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment) {
        Map<Enchantment, Integer> enchants = EnchantmentHelper.get(stack);
        return enchants.containsKey(enchantment);
    }

    /** Returnează nivelul enchantment-ului pe ItemStack (0 dacă nu există) */
    public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        Map<Enchantment, Integer> enchants = EnchantmentHelper.get(stack);
        return enchants.getOrDefault(enchantment, 0);
    }

    /** Returnează toate enchantment-urile și nivelele lor */
    public static Map<Enchantment, Integer> getEnchantments(ItemStack stack) {
        return EnchantmentHelper.get(stack);
    }
}
