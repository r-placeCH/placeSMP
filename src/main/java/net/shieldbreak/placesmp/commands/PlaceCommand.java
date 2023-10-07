package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlaceCommand implements CommandExecutor {

    private FileConfiguration config = Main.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!config.getBoolean("toggleworlds")) {
            player.sendMessage(Main.getPrefix() + "§cThis command is not enabled yet");
            return false;
        }

        player.teleport(new Location(Bukkit.getWorld("place"), 0, 62, 0));

        return false;
    }
}
