package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WebCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(Main.prefix + " §aUnsere Webseite: https://r-place.ch");
        return false;
    }
}
