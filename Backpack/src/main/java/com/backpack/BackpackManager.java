package com.backpack;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BackpackManager {

    private final Backpack plugin;
    private final File backpacksFolder;
    private final Map<UUID, Inventory> backpackCache;

    public BackpackManager(Backpack plugin) {
        this.plugin = plugin;
        this.backpacksFolder = new File(plugin.getDataFolder(), "backpacks");
        this.backpackCache = new HashMap<>();
        
        if (!backpacksFolder.exists()) {
            backpacksFolder.mkdirs();
        }
    }

    public Inventory getBackpack(Player player) {
        UUID uuid = player.getUniqueId();
        
        // Check cache first
        if (backpackCache.containsKey(uuid)) {
            return backpackCache.get(uuid);
        }
        
        // Load from file
        Inventory backpack = Bukkit.createInventory(null, 27, "Backpack");
        loadBackpack(player, backpack);
        backpackCache.put(uuid, backpack);
        
        return backpack;
    }

    public void saveBackpack(Player player, Inventory backpack) {
        File file = new File(backpacksFolder, player.getUniqueId().toString() + ".yml");
        FileConfiguration config = new YamlConfiguration();
        
        for (int i = 0; i < backpack.getSize(); i++) {
            ItemStack item = backpack.getItem(i);
            if (item != null) {
                config.set("items." + i, item);
            }
        }
        
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save backpack for " + player.getName());
            e.printStackTrace();
        }
    }

    private void loadBackpack(Player player, Inventory backpack) {
        File file = new File(backpacksFolder, player.getUniqueId().toString() + ".yml");
        
        if (!file.exists()) {
            return;
        }
        
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        
        for (String key : config.getConfigurationSection("items").getKeys(false)) {
            int slot = Integer.parseInt(key);
            ItemStack item = config.getItemStack("items." + key);
            if (item != null) {
                backpack.setItem(slot, item);
            }
        }
    }

    public void saveAllBackpacks() {
        for (Map.Entry<UUID, Inventory> entry : backpackCache.entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());
            if (player != null) {
                saveBackpack(player, entry.getValue());
            }
        }
    }

    public void removeFromCache(UUID uuid) {
        backpackCache.remove(uuid);
    }
}
