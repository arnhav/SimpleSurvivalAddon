package com.tyrriel.simplesurvivaladdons;

import com.tyrriel.simplesurvivaladdons.systems.fasttravel.FastTravelSystem;
import com.tyrriel.simplesurvivaladdons.systems.sexycrafting.SexyCraftingSystem;
import com.tyrriel.simplesurvivaladdons.systems.toolleveling.ToolLevelingSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleSurvivalAddons extends JavaPlugin {

    private ConsoleCommandSender console;

    @Override
    public void onEnable() {
        // Plugin startup logic

        console = getServer().getConsoleSender();

        // Enable message
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "+===================+");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "Plugin has been enabled!");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "Version: " + this.getDescription().getVersion());
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "+===================+");

        new ToolLevelingSystem(this);
        new FastTravelSystem(this);
        new SexyCraftingSystem(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Disable message
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "+===================+");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "Plugin has been disabled!");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[" + this.getName() + "] " + ChatColor.GREEN + "+===================+");
    }
}
