package net.shieldbreak.placesmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CreditsCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if (args.length != 0) return false;

        sender.sendMessage("");
        sender.sendMessage("§9obvTiger §7• §cPlugins §8»§a §7https://obvtiger.ch");
        sender.sendMessage("§x§6§2§0§0§f§fPandaDEV §7• §cPlugins §8» §7https://pandadev.tk");
        sender.sendMessage("§5Semxo §7• §cLobby §8» §7https://twitch.tv/semxo_tv");
        sender.sendMessage("§aShield-Break.net §7• §cHost §8» §7https://shield-break.net");
        sender.sendMessage("");

        return false;
    }
}
