package com.tyrriel.simplesurvivaladdons.systems.toolleveling;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelingListener implements Listener {

    JavaPlugin plugin;

    public LevelingListener(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event){
        if (event.getRecipe() == null) return;

        ItemStack itemStack = event.getRecipe().getResult();
        String type = itemStack.getType().toString();

        if (!type.contains("SWORD") || !type.contains("AXE") || !type.contains("SHOVEL") ||
                !type.contains("FISHING") || !type.contains("BOW") || !type.contains("TRIDENT"))
            return;

        Bukkit.getScheduler().runTaskLater(plugin, ()-> event.getInventory().setResult(ItemUtil.createTool(itemStack)),1);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();

        if (!ItemUtil.isTool(itemStack)) return;

        if (block.getType().toString().contains("LOG")){
            ItemUtil.increaseToolExp(itemStack, 2);
        }

        if (block.getType().toString().contains("ORE")){
            ItemUtil.increaseToolExp(itemStack, 5);
        }
    }

}
