package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        player.sendActionBar("§aDu wirst in 5 Sekunden Teleportiert...");
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
                World world = Bukkit.getWorld("world");
                Location loc = new Location(world,0.5,137,0.5);
                player.teleport(loc);
                player.sendActionBar("§aErfolgreich teleportiert!");
            }
        }, 5 * 20L);
        return false;
    }
}
