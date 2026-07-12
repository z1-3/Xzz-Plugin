package com.yourname.plugin.OreSystem;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;

public class Orechance implements Listener{
            OreListener listener;
            public Orechance(OreListener listener){
                this.listener = listener;
            }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Material type = event.getBlock().getType();
        Location location = event.getBlock().getLocation();
        if(type == Material.DIAMOND_ORE){
            event.setDropItems(false);
            if(Math.random() < listener.DiamondChance){
                ItemStack dia = new ItemStack(Material.DIAMOND);
                ItemMeta meta = dia.getItemMeta();
                meta.lore(List.of(
                    Component.text(listener.DiamondDescription)
                ));
                dia.setItemMeta(meta);
                event.getBlock().getWorld().dropItemNaturally(location, dia);
            }
        }
    }
}
