package com.backpack;

import org.bukkit.plugin.java.JavaPlugin;

public class Backpack extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Backpack plugin has been enabled!");
        
        // Register commands
        getCommand("backpack").setExecutor(new BackpackCommand(this));
        
        // Register events
        getServer().getPluginManager().registerEvents(new BackpackListener(this), this);
        
        // Save default config
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Backpack plugin has been disabled!");
    }
}
