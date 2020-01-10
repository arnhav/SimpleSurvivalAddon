package com.tyrriel.simplesurvivaladdons;

import com.tyrriel.simplesurvivaladdons.systems.fasttravel.FastTravelSystem;
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
        console.sendMessage(ChatColor.GOLD + "[" + this.getName() + "] " + ChatColor.AQUA + "+===================+");
        console.sendMessage(ChatColor.GOLD + "[" + this.getName() + "] " + ChatColor.AQUA + "Plugin has been enabled!");
        console.sendMessage(ChatColor.GOLD + "[" + this.getName() + "] " + ChatColor.AQUA + "Version: " + this.getDescription().getVersion());
        console.sendMessage(ChatColor.GOLD + "[" + this.getName() + "] " + ChatColor.AQUA + "+===================+");

        new ToolLevelingSystem(this);
        new FastTravelSystem(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Disable message
        console.sendMessage(ChatColor.GOLD + "[" + this.getName() + "] " + ChatColor.AQUA + "+===================+");
        console.sendMessage(ChatColor.AQUA + "[" + this.getName() + "] " + ChatColor.AQUA + "Plugin has been disabled!");
        console.sendMessage(ChatColor.GOLD + "[" + this.getName() + "] " + ChatColor.AQUA + "+===================+");
    }
}
