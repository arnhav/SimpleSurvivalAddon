package com.tyrriel.simplesurvivaladdons.systems.toolleveling;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class LevelingListener implements Listener {

    JavaPlugin plugin;
    Random rand = new Random();

    public LevelingListener(JavaPlugin plugin){
        this.plugin = plugin;
    }

    private boolean prob(double percent) {
        return rand.nextDouble() * 100 < percent;
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event){
        if (event.getRecipe() == null) return;

        ItemStack itemStack = event.getRecipe().getResult();
        String type = itemStack.getType().toString();
        if (!(type.contains("SWORD") || type.contains("AXE") || type.contains("SHOVEL") ||
                type.contains("FISHING") || type.contains("BOW") || type.contains("TRIDENT")))
            return;

        Bukkit.getScheduler().runTaskLater(plugin, ()->{
            event.getInventory().setResult(ItemUtil.createTool(itemStack));
            for (HumanEntity humanEntity : event.getViewers()){
                ((Player)humanEntity).updateInventory();
            }
        },1);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();

        if (!ItemUtil.isTool(itemStack)) return;

        switch (block.getType()){
            case COAL_ORE:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case IRON_ORE:
                ItemUtil.increaseToolExp(player, itemStack, 2);
                break;
            case NETHER_QUARTZ_ORE:
                ItemUtil.increaseToolExp(player, itemStack, 2);
                break;
            case GOLD_ORE:
                ItemUtil.increaseToolExp(player, itemStack, 3);
                break;
            case DIAMOND_ORE:
                ItemUtil.increaseToolExp(player, itemStack, 5);
                break;
            case EMERALD_ORE:
                ItemUtil.increaseToolExp(player, itemStack, 10);
                break;
            case OAK_LOG:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case BIRCH_LOG:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case DARK_OAK_LOG:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case JUNGLE_LOG:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case SPRUCE_LOG:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case ACACIA_LOG:
                ItemUtil.increaseToolExp(player, itemStack, 3);
                break;
            case GRASS_BLOCK:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;
            case CLAY:
                ItemUtil.increaseToolExp(player, itemStack, 1);
                break;

        }
        if (prob(ItemUtil.getToolLevel(itemStack) * 5)){
            for (ItemStack temp : block.getDrops(itemStack)) {
                block.getWorld().dropItemNaturally(block.getLocation(), temp);
            }
        }
    }

}
