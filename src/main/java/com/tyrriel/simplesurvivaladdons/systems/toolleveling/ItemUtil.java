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

    public static ItemStack createTool(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        pdc.set(ItemUtil.level, PersistentDataType.INTEGER, 0);
        pdc.set(ItemUtil.exp, PersistentDataType.INTEGER, 0);

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

        int level = pdc.get(ItemUtil.level, PersistentDataType.INTEGER);
        int exp = pdc.get(ItemUtil.exp, PersistentDataType.INTEGER);

        if (exp + amount >= Math.pow(25, level)){
            exp = (int)Math.pow(25, level) - exp;
            increaseToolLevel(itemStack);
        }

        pdc.set(ItemUtil.level, PersistentDataType.INTEGER, level);
        pdc.set(ItemUtil.exp, PersistentDataType.INTEGER, exp);

        itemMeta.setLore(Arrays.asList(
                " ",
                ChatColor.AQUA + "Level: " + ChatColor.WHITE + level,
                ChatColor.AQUA + "Experience: " + ChatColor.WHITE + exp + "/" + Math.pow(25, level),
                " "
        ));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack increaseToolLevel(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        int level = pdc.get(ItemUtil.level, PersistentDataType.INTEGER) + 1;

        pdc.set(ItemUtil.level, PersistentDataType.INTEGER, level);
        pdc.set(ItemUtil.exp, PersistentDataType.INTEGER, 0);

        itemMeta.setLore(Arrays.asList(
                " ",
                ChatColor.AQUA + "Level: " + ChatColor.WHITE + level,
                ChatColor.AQUA + "Experience: " + ChatColor.WHITE + "0/" + Math.pow(25, level),
                " "
        ));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
