package com.yourname.plugin.OreSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class OreListener implements Listener {

    public Set<UUID> waitingDescripion = new HashSet<>();

    public double DiamondChance = 1;
    public String DiamondDescription = "";

    public double IronChance = 1;
    public String IronDescription = "";

    public double GoldChance = 1;
    public String GoldDescription = "";

    public double CoalChance = 1;
    public String CoalDescription = "";

public Inventory CreateOreSetting(String oreName, double orechance, String description){
        Inventory gui = Bukkit.createInventory(null, 27, oreName);
        ItemStack clock = new ItemStack(Material.CLOCK);
        ItemMeta metac = clock.getItemMeta();
        metac.displayName(
          Component.text("Diamond Chance: "+ orechance)  
        );
        
        clock.setItemMeta(metac);

        ItemStack desc = new ItemStack(Material.INK_SAC);
        ItemMeta metasec = desc.getItemMeta();
        metasec.displayName(
            Component.text(description)
        );

        desc.setItemMeta(metasec);

        gui.setItem(11, clock);
        gui.setItem(14, desc);
        return gui;
    }

public  double clickAction(InventoryClickEvent e ,String metaOre, double orechance){
    Player p = (Player) e.getWhoClicked();
     if (e.getCurrentItem().getType() == Material.CLOCK) {
            orechance += 0.1;

        if(orechance > 1) {
             orechance = 0;
    }

        ItemStack item = e.getCurrentItem();
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(metaOre + orechance));
        item.setItemMeta(meta);
    }
     if(e.getCurrentItem().getType() == Material.INK_SAC){
        waitingDescripion.add(p.getUniqueId());
        p.closeInventory();
      p.sendMessage("§eEnterTheDescription");
     }

    return orechance;

    
}



    @EventHandler
    public void onClick(InventoryClickEvent event) {

        String title = event.getView().getTitle();
        Material type = event.getCurrentItem().getType();
        Player player = (Player) event.getWhoClicked();

        if (title.equals("OreMain")) {
            event.setCancelled(true);
             if (event.getCurrentItem() == null)return;
        
        if(type == Material.DIAMOND_BLOCK ){
            player.openInventory(CreateOreSetting("Diamond Setting", DiamondChance, DiamondDescription));
        }      
             
}
        if (title.equals("Diamond Setting")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null)return;
            DiamondChance = clickAction(event, "Diamond Chance is: ", DiamondChance);
        }

        if(title.equals("Iron Setting")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            IronChance = clickAction(event, "Iron Chance is: ", IronChance);
        }
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        Player player = event.getPlayer();

        if (!waitingDescripion.contains(player.getUniqueId())) {
            return;
        }

        event.setCancelled(true);

        DiamondDescription = PlainTextComponentSerializer.plainText()
                .serialize(event.message());

        waitingDescripion.remove(player.getUniqueId());

        player.sendMessage("§aDescription Saved!");
    }
}