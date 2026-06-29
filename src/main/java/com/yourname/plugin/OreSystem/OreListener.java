package com.yourname.plugin.OreSystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class OreListener implements Listener {
    public void onClick(InventoryClickEvent event){

        if(!(event.getView().getTitle().equals("OreMain"))){
            return;
        }

        event.setCancelled(true);

        if(event.getCurrentItem() == null){
            return;
        }

        Material type = event.getCurrentItem().getType();

        if(type == Material.DIAMOND_BLOCK){
            Inventory Diamondgui = Bukkit.createInventory(null, 27, "Diamond Setting");
            Player player = (Player) event.getWhoClicked();
            player.openInventory(Diamondgui);

        }


    }
}
