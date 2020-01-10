package com.tyrriel.simplesurvivaladdons.systems.toolleveling;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolLevelingSystem {

    public ToolLevelingSystem(JavaPlugin plugin){
        new ItemUtil(plugin);

        Bukkit.getPluginManager().registerEvents(new LevelingListener(plugin), plugin);
    }

}
