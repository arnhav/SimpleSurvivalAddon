package com.tyrriel.simplesurvivaladdons.systems.toolleveling;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class ItemUtil {

    public static NamespacedKey level;
    public static NamespacedKey exp;

    public ItemUtil(JavaPlugin plugin){
        level = new NamespacedKey(plugin, "Level");
        exp = new NamespacedKey(plugin, "Exp");
    }

    public static boolean isTool(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        return pdc.has(level, PersistentDataType.INTEGER);
    }

    public static int getToolLevel(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        return pdc.get(level, PersistentDataType.INTEGER);
    }

    public static int getToolExp(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        return pdc.get(exp, PersistentDataType.INTEGER);
    }

    public static ItemStack createTool(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        pdc.set(level, PersistentDataType.INTEGER, 0);
        pdc.set(exp, PersistentDataType.INTEGER, 0);

        itemMeta.setLore(Arrays.asList(
                " ",
                ChatColor.AQUA + "Level: " + ChatColor.WHITE + 1,
                ChatColor.AQUA + "Experience: " + ChatColor.WHITE + "0/" + Math.pow(25, 1),
                " "
        ));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack increaseToolExp(ItemStack itemStack, int amount){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        int l = getToolLevel(itemStack);
        int e = getToolExp(itemStack);

        if (e + amount >= Math.pow(25, l)){
            e = (int)Math.pow(25, l) - e;
            increaseToolLevel(itemStack);
            l = getToolLevel(itemStack);
        }

        pdc.set(ItemUtil.level, PersistentDataType.INTEGER, l);
        pdc.set(ItemUtil.exp, PersistentDataType.INTEGER, e);

        itemMeta.setLore(Arrays.asList(
                " ",
                ChatColor.AQUA + "Level: " + ChatColor.WHITE + l,
                ChatColor.AQUA + "Experience: " + ChatColor.WHITE + e + "/" + Math.pow(25, l),
                " "
        ));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack increaseToolLevel(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        int l = getToolLevel(itemStack) + 1;

        pdc.set(ItemUtil.level, PersistentDataType.INTEGER, l);
        pdc.set(ItemUtil.exp, PersistentDataType.INTEGER, 0);

        itemMeta.setLore(Arrays.asList(
                " ",
                ChatColor.AQUA + "Level: " + ChatColor.WHITE + l,
                ChatColor.AQUA + "Experience: " + ChatColor.WHITE + "0/" + Math.pow(25, l),
                " "
        ));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
