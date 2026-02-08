package com.backpack;

import org.bukkit.plugin.java.JavaPlugin;

public class Backpack extends JavaPlugin {

    private BackpackManager backpackManager;

    @Override
    public void onEnable() {
        getLogger().info("Backpack plugin has been enabled!");
        
        // Initialize backpack manager
        backpackManager = new BackpackManager(this);
        
        // Register events
        getServer().getPluginManager().registerEvents(new BackpackListener(this), this);
        
        // Save default config
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Backpack plugin has been disabled!");
        
        // Save all backpacks before shutdown
        if (backpackManager != null) {
            backpackManager.saveAllBackpacks();
        }
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }
}
