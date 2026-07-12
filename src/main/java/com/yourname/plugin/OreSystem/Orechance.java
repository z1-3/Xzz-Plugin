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
public void dropChance(BlockBreakEvent e, Material typeOre, double chance, String metaOre) {
    Material type = e.getBlock().getType();
    Location location = e.getBlock().getLocation();

    if (type == typeOre) {
        e.setDropItems(false);

        if (Math.random() < chance) {
            ItemStack item = new ItemStack(typeOre);

            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text(metaOre));
            item.setItemMeta(meta);

            location.getWorld().dropItemNaturally(location, item);
        }
    }
}
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        dropChance(e,Material.DIAMOND,listener.DiamondChance,listener.DiamondDescription);
        dropChance(e,Material.RAW_IRON,listener.IronChance,listener.IronDescription);
        dropChance(e,Material.RAW_GOLD,listener.GoldChance,listener.GoldDescription);
        dropChance(e,Material.COAL,listener.CoalChance,listener.CoalDescription);

       
    }
}
