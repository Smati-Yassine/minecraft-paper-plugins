package com.backpack;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
        if (player.isSneaking()) {
            event.setCancelled(true);
            
            if (!player.hasPermission("backpack.use")) {
                player.sendMessage("You don't have permission to use backpacks!");
                return;
            }
            
            // Get or create backpack from manager
            Inventory backpack = plugin.getBackpackManager().getBackpack(player);
            player.openInventory(backpack);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("Backpack")) {
            Player player = (Player) event.getPlayer();
            Inventory backpack = event.getInventory();
            
            // Save backpack contents
            plugin.getBackpackManager().saveBackpack(player, backpack);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Inventory backpack = plugin.getBackpackManager().getBackpack(player);
        
        // Save backpack on logout
        plugin.getBackpackManager().saveBackpack(player, backpack);
        
        // Remove from cache to free memory
        plugin.getBackpackManager().removeFromCache(player.getUniqueId());
    }
}
