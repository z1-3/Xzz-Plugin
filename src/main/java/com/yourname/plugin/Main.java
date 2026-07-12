package com.yourname.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.yourname.plugin.OreSystem.OreGui;
import com.yourname.plugin.OreSystem.OreListener;
import com.yourname.plugin.OreSystem.Orechance;


public class Main extends JavaPlugin {
    OreListener oreListener = new OreListener();
    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled!");

        getServer().getPluginManager().registerEvents(oreListener, this);
        getServer().getPluginManager().registerEvents(new Orechance(oreListener), this);
        
        
        getCommand("oregui").setExecutor(new OreGui());

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}