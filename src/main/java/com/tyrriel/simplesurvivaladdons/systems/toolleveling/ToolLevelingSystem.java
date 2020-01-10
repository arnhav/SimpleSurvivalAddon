package com.tyrriel.simplesurvivaladdons.systems.toolleveling;

import org.bukkit.plugin.java.JavaPlugin;

public class ToolLevelingSystem {

    public ToolLevelingSystem(JavaPlugin plugin){
        new ItemUtil(plugin);

        plugin.getServer().getPluginManager().registerEvents(new LevelingListener(plugin), plugin);
    }
}
