package com.yourname.plugin.OreSystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;

public class OreListener implements Listener {
    public  Set<UUID> waitingDescripion = new HashSet<>();
    public int DiamondChance = 10;

    @EventHandler
    public void onClick(InventoryClickEvent event){
        
        String title = event.getView().getTitle();

        if(title.equals("OreMain")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            Inventory Diamondgui = Bukkit.createInventory(null, 27, "Diamond Setting");
            Player player = (Player) event.getWhoClicked();
            ItemStack diachen = new ItemStack(Material.CLOCK);
            ItemStack descripion = new ItemStack(Material.INK_SAC);

            ItemMeta meta = diachen.getItemMeta();
            meta.displayName(Component.text("Diamond Chance: " + DiamondChance));
            diachen.setItemMeta(meta);

            Diamondgui.setItem(12, diachen);
            Diamondgui.setItem(14, descripion);
            player.openInventory(Diamondgui);


            
            
        }

        if(title.equals("Diamond Setting")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            if(event.getCurrentItem().getType() == Material.CLOCK){
                DiamondChance += 10;

                ItemStack item = event.getCurrentItem();
                ItemMeta meta = item.getItemMeta();

                meta.displayName(Component.text("Diamond Chance: "+ DiamondChance));

                item.setItemMeta(meta);

                if (DiamondChance == 100) {
                    DiamondChance = 0;
                }
            }
        }

    }
}
