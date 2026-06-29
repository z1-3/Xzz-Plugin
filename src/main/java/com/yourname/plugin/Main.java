package com.yourname.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.yourname.plugin.OreSystem.OreGui;
import com.yourname.plugin.OreSystem.OreListener;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled!");

        getServer().getPluginManager().registerEvents(new OreListener(), this);
        
        
        getCommand("oregui").setExecutor(new OreGui());

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}