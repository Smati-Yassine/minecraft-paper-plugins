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

        // Check if admin is trying to view another player's backpack
        if (args.length > 0) {
            if (!player.hasPermission("backpack.admin")) {
                player.sendMessage("You don't have permission to view other players' backpacks!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("Player not found!");
                return true;
            }

            // Open target player's backpack
            Inventory backpack = plugin.getBackpackManager().getBackpack(target);
            player.openInventory(backpack);
            player.sendMessage("Opening " + target.getName() + "'s backpack...");
            return true;
        }

        // Open own backpack
        if (!player.hasPermission("backpack.use")) {
            player.sendMessage("You don't have permission to use backpacks!");
            return true;
        }

        Inventory backpack = plugin.getBackpackManager().getBackpack(player);
        player.openInventory(backpack);
        return true;
    }
}
