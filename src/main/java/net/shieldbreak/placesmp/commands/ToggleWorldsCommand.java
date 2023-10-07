package net.shieldbreak.placesmp.commands;

import net.shieldbreak.placesmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sun.security.krb5.Config;

public class ToggleWorldsCommand implements CommandExecutor {

    private FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + "§6This command can only be run by a player!");
            return false;
        }

        Player player = (Player) (sender);

        if (args.length == 0){
            if (config.getBoolean("toggleworlds")){
                config.set("toggleworlds", false);
            } else {
                config.set("toggleworlds", true);
            }
            Main.getInstance().saveConfig();

            player.sendMessage(Main.getPrefix() + "§7World commands have been toggled §a" + (config.getBoolean("toggleworlds") ? "on" : "off"));
        } else {
            player.sendMessage(Main.getPrefix() + "§c/toggleworlds");
        }

        return false;
    }

}