package com.yourname.plugin.OreSystem;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Orechance implements Listener{
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(event.getBlock().getType() == Material.DIAMOND_BLOCK){
            event.setDropItems(false);
        }
    }
}
