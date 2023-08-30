package net.shieldbreak.placesmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CreditsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("§7Lobby: §5Semxo §7|§a https://twitch.tv/semxo_tv");
        sender.sendMessage("§7Plugin: §9obvTiger §7|§a https://obvtiger.ch");
        sender.sendMessage("§7Server: §aShield-Break.net §7|§a https://shield-break.net");
        sender.sendMessage("");
        return false;
    }
}
