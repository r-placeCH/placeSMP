package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if (args.length != 0) return false;

        sender.sendMessage(Main.prefix + "§9Discord: https://discord.r-place.ch");

        return false;
    }
}