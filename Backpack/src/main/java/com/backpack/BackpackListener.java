package com.backpack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;

public class BackpackListener implements Listener {

    private final Backpack plugin;

    public BackpackListener(Backpack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSwapHands(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        
        // Check if player is sneaking (shift) and pressing F (swap hands key)
        // This is the closest we can get to detecting a key press in Bukkit
        // Players will need to press Shift+F to open backpack
        if (player.isSneaking()) {
            event.setCancelled(true);
            
            if (!player.hasPermission("backpack.use")) {
                player.sendMessage("You don't have permission to use backpacks!");
                return;
            }
            
            // Create a backpack inventory (27 slots = 3 rows)
            Inventory backpack = Bukkit.createInventory(null, 27, "Backpack");
            player.openInventory(backpack);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("Backpack")) {
            // Handle backpack close event
            // You can save inventory contents here
        }
    }
}
