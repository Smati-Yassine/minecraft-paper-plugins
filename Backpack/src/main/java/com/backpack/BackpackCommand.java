package com.backpack;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BackpackCommand implements CommandExecutor {

    private final Backpack plugin;

    public BackpackCommand(Backpack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("backpack.use")) {
            player.sendMessage("You don't have permission to use backpacks!");
            return true;
        }

        // Create a backpack inventory (27 slots = 3 rows)
        Inventory backpack = Bukkit.createInventory(null, 27, "Backpack");
        
        player.openInventory(backpack);
        return true;
    }
}
