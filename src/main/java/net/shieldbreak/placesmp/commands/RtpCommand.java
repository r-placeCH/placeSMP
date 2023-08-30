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
        player.sendMessage(Main.prefix + "§7Du wurdest an einen zufälligen Ort teleportiert");

        return false;
    }

    private int getHighestBlockY(World world, int x, int z) {
        int maxY = world.getMaxHeight();
        for (int y = maxY - 1; y >= 0; y--) {
            Location location = new Location(world, x, y, z);
            Block block = location.getBlock();
            if (block.getType() == Material.WATER) {
                getHighestBlockY(world,x,z);
            }
            if (block.getType() == Material.AIR) {
                return y;
            }

        }
        return 0;
    }



    private static boolean isSafe(Location location) {
        World world = location.getWorld();

        Block legs = world.getBlockAt(location);
        Block below = world.getBlockAt(location.add(0, -1, 0));
        Block above = world.getBlockAt(location.add(0, 2, 0));

        return below.getType().isSolid()
                && below.getType().isOccluding()
                && (above.getType() == Material.AIR)
                && (legs.getType() == Material.AIR);
    }

    private static Location getRandomLocation(World world, int radius, Location centre) {
        int maxX = centre.getBlockX() + radius;
        int minX = centre.getBlockX() - radius;

        int maxZ = centre.getBlockZ() + radius;
        int minZ = centre.getBlockZ() - radius;

        Random r = new Random();

        int ix = r.nextInt(Math.max(Math.abs(maxX - minX), 1)) + minX;
        double x = ix + 0.5;
        int iz = r.nextInt(Math.max(Math.abs(maxZ - minZ), 1)) + minZ;
        double z = iz + 0.5;

        return new Location(world, x, world.getHighestBlockYAt(ix, iz), z);
    }

    private void teleport(Player player){


        Location playerTpLocation = getRandomLocation(Bukkit.getWorld("world"),10000,new Location(Bukkit.getWorld("world",0,0,));

        //System.out.println(checkWater(player));

        player.teleport(new Location(Bukkit.getWorld("world"),
                x,
                getHighestBlockY(Bukkit.getWorld("world"), x, z) + 1,
                z));
    }

}