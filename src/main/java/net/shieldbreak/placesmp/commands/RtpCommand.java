package net.shieldbreak.placesmp.commands;

import jdk.javadoc.internal.doclint.HtmlTag;
import net.shieldbreak.placesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class RtpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.prefix + "§6Du musst diesen Befehl als Spieler ausführen!");
            return false;
        }

        Player player = (Player) (sender);

        if (args.length != 0) return false;

        teleport(player);
        player.sendMessage(Main.prefix + "§7Du wurdest an einen zufälligen ort teleportiert");

        return false;
    }

    private int getHighestBlockY(World world, int x, int z) {
        int maxY = world.getMaxHeight();
        for (int y = maxY - 1; y >= 0; y--) {
            Location location = new Location(world, x, y, z);
            Block block = location.getBlock();
            if (!block.isEmpty()) {
                return y;
            }
        }
        return 0;
    }

    private Block getHighestBlock(World world, int x, int z) {
        int maxY = world.getMaxHeight();
        for (int y = maxY - 1; y >= 0; y--) {
            Location location = new Location(world, x, y, z);
            Block block = location.getBlock();
            if (!block.isEmpty()) {
                return block;
            }
        }
        return null;
    }

    private void teleport(Player player){
        int x = Integer.parseInt(String.valueOf(new Random().nextInt(10000 - 500 + 1) + 500));
        int z = Integer.parseInt(String.valueOf(new Random().nextInt(10000 - 500 + 1) + 500));

        if (getHighestBlock(Bukkit.getWorld("smp"), x, z).getType().equals(Material.WATER)) {
            teleport(player);
        }

        player.teleport(new Location(Bukkit.getWorld("smp"),
                x,
                getHighestBlockY(Bukkit.getWorld("smp"), x, z),
                z));
    }

}