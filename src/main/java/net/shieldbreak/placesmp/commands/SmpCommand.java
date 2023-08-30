package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SmpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        player.sendActionBar("§aDu wirst in 5 Sekunden Teleportiert...");
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
                World world = Bukkit.getWorld("SMP");
                Location loc = new Location(world,3.5,137,1.5);
                player.teleport(loc);
                player.sendActionBar("§aErfolgreich teleportiert!");
            }
        }, 5 * 20L);
        return false;
    }
}
