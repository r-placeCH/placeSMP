package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SmpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.teleport(new Location(Bukkit.getWorld("SMP"), 3.5, 137, 1.5));

        return false;
    }
}
