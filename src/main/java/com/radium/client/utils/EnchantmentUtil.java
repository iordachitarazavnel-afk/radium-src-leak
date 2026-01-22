package com.radium.client.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Map;

public class EnchantmentUtil {

    /**
     * Verifică dacă un ItemStack are un anumit enchantment.
     * @param stack ItemStack-ul
     * @param enchantment Enchantment-ul căutat
     * @return true dacă există
     */
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment) {
        if (stack == null || enchantment == null) return false;
        if (!stack.hasEnchantments()) return false;

        // Obținem enchantments ca Map<Enchantment, Integer>
        Map<Enchantment, Integer> enchants = stack.getEnchantments();
        return enchants.containsKey(enchantment);
    }

    /**
     * Obține nivelul unui enchantment pe un ItemStack.
     * @param stack ItemStack-ul
     * @param enchantment Enchantment-ul căutat
     * @return nivelul sau 0 dacă nu există
     */
    public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        if (stack == null || enchantment == null) return 0;
        if (!stack.hasEnchantments()) return 0;

        Map<Enchantment, Integer> enchants = stack.getEnchantments();
        return enchants.getOrDefault(enchantment, 0);
    }

    /**
     * Verifică dacă un ItemStack are orice enchantment.
     * @param stack ItemStack-ul
     * @return true dacă are cel puțin un enchantment
     */
    public static boolean hasAnyEnchantment(ItemStack stack) {
        if (stack == null) return false;
        return stack.hasEnchantments() && !stack.getEnchantments().isEmpty();
    }

    /**
     * Exemplu: verifică dacă un item este unelte (tools) sau armură
     */
    public static boolean isWeapon(ItemStack stack) {
        if (stack == null) return false;
        return stack.getItem() == Items.DIAMOND_SWORD || stack.getItem() == Items.IRON_SWORD;
    }
}
