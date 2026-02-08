package com.backpack;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class BackpackListener implements Listener {

    private final Backpack plugin;

    public BackpackListener(Backpack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("Backpack")) {
            // Handle backpack close event
            // You can save inventory contents here
        }
    }
}
