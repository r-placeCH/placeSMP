package net.shieldbreak.placesmp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Listeners implements Listener {


    private static final ArrayList<Player> delayedPlayers = new ArrayList<Player>();

    private boolean isWhitelisted(Player player) {
        try {
            String endpoint = "https://wireway.ch/api/placeMC/check/?username=" + player.getName();
            URL url = new URL(endpoint);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String response = in.readLine();
            in.close();

            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(response).getAsJsonObject();
            boolean result = json.get("result").getAsBoolean();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!(e.getPlayer().hasPlayedBefore())) {
            Location loc = new Location(Bukkit.getWorld("world"), 0.5, 137, 0.5);
            e.getPlayer().teleport(loc);
            Inventory inv = e.getPlayer().getInventory();
            inv.setItem(0, new ItemStack(Material.STONE_SWORD));
            inv.setItem(1, new ItemStack(Material.STONE_PICKAXE));
            inv.setItem(2, new ItemStack(Material.STONE_AXE));
            inv.setItem(3, new ItemStack(Material.STONE_SHOVEL));
            inv.setItem(4, new ItemStack(Material.COOKED_BEEF));
            Objects.requireNonNull(inv.getItem(4)).setAmount(32);
        }

        //////////////////////// whitelist check ////////////////////////

        Player player = e.getPlayer();

        if (!isWhitelisted(player)) {
            e.setJoinMessage("");
            player.kickPlayer("§c§lYou are not whitelisted on this server!\n\n§7You can get whitelisted by joining our discord \nand sending your name into the whitelist channel.\n\n§9discord.r-place.ch");
        } else {
            e.setJoinMessage(Main.prefix + " §a+ " + e.getPlayer().getName());
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!isWhitelisted(e.getPlayer())) {
            e.setQuitMessage("");
        } else {
            e.setQuitMessage(Main.prefix + " §a- " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setDeathMessage("§c☠ §7" + e.getEntity().getPlayer().getName());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerRespawnEvent(PlayerRespawnEvent e) {
        e.setRespawnLocation(new Location(Bukkit.getWorld("world"), 0.5, 137, 0.5));
        e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 137, 0.5));
    }


    @EventHandler
    public void onCanvasPlace(BlockPlaceEvent e) {

        List<Material> materials = new ArrayList<>();

        // Fences
        materials.add(Material.ACACIA_FENCE);
        materials.add(Material.BIRCH_FENCE);
        materials.add(Material.DARK_OAK_FENCE);
        materials.add(Material.JUNGLE_FENCE);
        materials.add(Material.OAK_FENCE);
        materials.add(Material.SPRUCE_FENCE);
        materials.add(Material.NETHER_BRICK_FENCE);
        materials.add(Material.WARPED_FENCE);
        materials.add(Material.CRIMSON_FENCE);

        // Doors
        materials.add(Material.ACACIA_DOOR);
        materials.add(Material.BIRCH_DOOR);
        materials.add(Material.DARK_OAK_DOOR);
        materials.add(Material.JUNGLE_DOOR);
        materials.add(Material.OAK_DOOR);
        materials.add(Material.SPRUCE_DOOR);
        materials.add(Material.IRON_DOOR);

        // Trapdoors
        materials.add(Material.ACACIA_TRAPDOOR);
        materials.add(Material.BIRCH_TRAPDOOR);
        materials.add(Material.DARK_OAK_TRAPDOOR);
        materials.add(Material.JUNGLE_TRAPDOOR);
        materials.add(Material.OAK_TRAPDOOR);
        materials.add(Material.SPRUCE_TRAPDOOR);
        materials.add(Material.IRON_TRAPDOOR);

        // Iron Bars, Chain
        materials.add(Material.IRON_BARS);
        materials.add(Material.CHAIN);

        // Carpets
        materials.add(Material.BLACK_CARPET);
        materials.add(Material.BLUE_CARPET);
        materials.add(Material.BROWN_CARPET);
        materials.add(Material.CYAN_CARPET);
        materials.add(Material.GRAY_CARPET);
        materials.add(Material.GREEN_CARPET);
        materials.add(Material.LIGHT_BLUE_CARPET);
        materials.add(Material.LIGHT_GRAY_CARPET);
        materials.add(Material.LIME_CARPET);
        materials.add(Material.MAGENTA_CARPET);
        materials.add(Material.ORANGE_CARPET);
        materials.add(Material.PINK_CARPET);
        materials.add(Material.PURPLE_CARPET);
        materials.add(Material.RED_CARPET);
        materials.add(Material.WHITE_CARPET);
        materials.add(Material.YELLOW_CARPET);

        // Glass Panes
        materials.add(Material.GLASS_PANE);
        materials.add(Material.BLACK_STAINED_GLASS_PANE);
        materials.add(Material.BLUE_STAINED_GLASS_PANE);
        materials.add(Material.BROWN_STAINED_GLASS_PANE);
        materials.add(Material.CYAN_STAINED_GLASS_PANE);
        materials.add(Material.GRAY_STAINED_GLASS_PANE);
        materials.add(Material.GREEN_STAINED_GLASS_PANE);
        materials.add(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        materials.add(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        materials.add(Material.LIME_STAINED_GLASS_PANE);
        materials.add(Material.MAGENTA_STAINED_GLASS_PANE);
        materials.add(Material.ORANGE_STAINED_GLASS_PANE);
        materials.add(Material.PINK_STAINED_GLASS_PANE);
        materials.add(Material.PURPLE_STAINED_GLASS_PANE);
        materials.add(Material.RED_STAINED_GLASS_PANE);
        materials.add(Material.WHITE_STAINED_GLASS_PANE);
        materials.add(Material.YELLOW_STAINED_GLASS_PANE);

        // Candles
        materials.add(Material.CANDLE);
        materials.add(Material.BLACK_CANDLE);
        materials.add(Material.BLUE_CANDLE);
        materials.add(Material.BROWN_CANDLE);
        materials.add(Material.CYAN_CANDLE);
        materials.add(Material.GRAY_CANDLE);
        materials.add(Material.GREEN_CANDLE);
        materials.add(Material.LIGHT_BLUE_CANDLE);
        materials.add(Material.LIGHT_GRAY_CANDLE);
        materials.add(Material.LIME_CANDLE);
        materials.add(Material.MAGENTA_CANDLE);
        materials.add(Material.ORANGE_CANDLE);
        materials.add(Material.PINK_CANDLE);
        materials.add(Material.PURPLE_CANDLE);
        materials.add(Material.RED_CANDLE);
        materials.add(Material.WHITE_CANDLE);
        materials.add(Material.YELLOW_CANDLE);

        // Banners
        materials.add(Material.BLACK_BANNER);
        materials.add(Material.BLUE_BANNER);
        materials.add(Material.BROWN_BANNER);
        materials.add(Material.CYAN_BANNER);
        materials.add(Material.GRAY_BANNER);
        materials.add(Material.GREEN_BANNER);
        materials.add(Material.LIGHT_BLUE_BANNER);
        materials.add(Material.LIGHT_GRAY_BANNER);
        materials.add(Material.LIME_BANNER);
        materials.add(Material.MAGENTA_BANNER);
        materials.add(Material.ORANGE_BANNER);
        materials.add(Material.PINK_BANNER);
        materials.add(Material.PURPLE_BANNER);
        materials.add(Material.RED_BANNER);
        materials.add(Material.WHITE_BANNER);
        materials.add(Material.YELLOW_BANNER);

        // Snow, Moss Carpet, Pointed Dripstone
        materials.add(Material.SNOW);
        materials.add(Material.MOSS_CARPET);
        materials.add(Material.POINTED_DRIPSTONE);

        // Amethyst Bud/Cluster
        materials.add(Material.SMALL_AMETHYST_BUD);
        materials.add(Material.MEDIUM_AMETHYST_BUD);
        materials.add(Material.LARGE_AMETHYST_BUD);
        materials.add(Material.AMETHYST_CLUSTER);

        // Saplings
        materials.add(Material.ACACIA_SAPLING);
        materials.add(Material.BIRCH_SAPLING);
        materials.add(Material.DARK_OAK_SAPLING);
        materials.add(Material.JUNGLE_SAPLING);
        materials.add(Material.OAK_SAPLING);
        materials.add(Material.SPRUCE_SAPLING);

        // Mushrooms
        materials.add(Material.BROWN_MUSHROOM);
        materials.add(Material.RED_MUSHROOM);

        // Flowers
        materials.add(Material.DANDELION);
        materials.add(Material.POPPY);
        materials.add(Material.BLUE_ORCHID);
        materials.add(Material.ALLIUM);
        materials.add(Material.AZURE_BLUET);
        materials.add(Material.RED_TULIP);
        materials.add(Material.ORANGE_TULIP);
        materials.add(Material.WHITE_TULIP);
        materials.add(Material.PINK_TULIP);
        materials.add(Material.OXEYE_DAISY);
        materials.add(Material.CORNFLOWER);
        materials.add(Material.LILY_OF_THE_VALLEY);
        materials.add(Material.WITHER_ROSE);

        // Vines
        materials.add(Material.VINE);
        materials.add(Material.TWISTING_VINES);
        materials.add(Material.WEEPING_VINES);

        // Corals
        materials.add(Material.BRAIN_CORAL);
        materials.add(Material.BUBBLE_CORAL);
        materials.add(Material.FIRE_CORAL);
        materials.add(Material.HORN_CORAL);
        materials.add(Material.TUBE_CORAL);

        // Sculk Vein
        materials.add(Material.SCULK_VEIN);

        // Fakeln (Torches)
        materials.add(Material.TORCH);
        materials.add(Material.SOUL_TORCH);
        materials.add(Material.REDSTONE_TORCH);

        // Cobweb, End Rod, End Crystal
        materials.add(Material.COBWEB);
        materials.add(Material.END_ROD);
        materials.add(Material.END_CRYSTAL);

        // Brewing Stand, Cauldron, Bell, Conduit
        materials.add(Material.BREWING_STAND);
        materials.add(Material.CAULDRON);
        materials.add(Material.BELL);
        materials.add(Material.CONDUIT);

        // Ladders, Lightning Rod, Flower Pot
        materials.add(Material.LADDER);
        materials.add(Material.LIGHTNING_ROD);
        materials.add(Material.FLOWER_POT);

        // Armor Stand, Item Frames
        materials.add(Material.ARMOR_STAND);
        materials.add(Material.ITEM_FRAME);

        // Signs
        materials.add(Material.ACACIA_SIGN);
        materials.add(Material.BIRCH_SIGN);
        materials.add(Material.DARK_OAK_SIGN);
        materials.add(Material.JUNGLE_SIGN);
        materials.add(Material.OAK_SIGN);
        materials.add(Material.SPRUCE_SIGN);
        materials.add(Material.WARPED_SIGN);
        materials.add(Material.CRIMSON_SIGN);

        // Player Heads
        materials.add(Material.PLAYER_HEAD);
        materials.add(Material.PLAYER_WALL_HEAD);

        // Ender Egg, Redstone, Repeater, Comparator, Lever
        materials.add(Material.DRAGON_EGG);
        materials.add(Material.REDSTONE);
        materials.add(Material.REPEATER);
        materials.add(Material.COMPARATOR);
        materials.add(Material.LEVER);

        // Tripwire Hook, String, Hopper
        materials.add(Material.TRIPWIRE_HOOK);
        materials.add(Material.STRING);
        materials.add(Material.HOPPER);

        // Tracks
        materials.add(Material.RAIL);
        materials.add(Material.POWERED_RAIL);
        materials.add(Material.DETECTOR_RAIL);
        materials.add(Material.ACTIVATOR_RAIL);

        // Minecarts
        materials.add(Material.MINECART);
        materials.add(Material.CHEST_MINECART);
        materials.add(Material.FURNACE_MINECART);
        materials.add(Material.TNT_MINECART);
        materials.add(Material.HOPPER_MINECART);
        materials.add(Material.COMMAND_BLOCK_MINECART);

        //Beds
        materials.add(Material.WHITE_BED);
        materials.add(Material.ORANGE_BED);
        materials.add(Material.MAGENTA_BED);
        materials.add(Material.LIGHT_BLUE_BED);
        materials.add(Material.YELLOW_BED);
        materials.add(Material.LIME_BED);
        materials.add(Material.PINK_BED);
        materials.add(Material.GRAY_BED);
        materials.add(Material.LIGHT_GRAY_BED);
        materials.add(Material.CYAN_BED);
        materials.add(Material.PURPLE_BED);
        materials.add(Material.BLUE_BED);
        materials.add(Material.BROWN_BED);
        materials.add(Material.GREEN_BED);
        materials.add(Material.RED_BED);
        materials.add(Material.BLACK_BED);

        // TNT
        materials.add(Material.TNT);
        if(e.getPlayer().getWorld().getName().equals("world") && !(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {

            e.setCancelled(true);
        }

        if(e.getPlayer() instanceof Player) {
            if(e.getPlayer().getWorld().getName().equals("place")) {
                if (e.getBlockPlaced().getY() == 61) {

                    if (delayedPlayers.contains(e.getPlayer())){
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7« §6Du kannst für 5 sekunden keinen block platzieren §7»"));
                        e.setCancelled(true);
                        return;
                    }

                    Material blockMaterialToPlace = e.getBlock().getType();
                    if(!(materials.contains(blockMaterialToPlace))) {
                        Location blockLocationToPlace = new Location(Bukkit.getWorld("place"), e.getBlockPlaced().getX(), e.getBlockPlaced().getY() - 1, e.getBlockPlaced().getZ());
                        blockLocationToPlace.getBlock().setType(blockMaterialToPlace);
                        Player player = e.getPlayer();
                        ItemStack itemInHand = player.getInventory().getItemInMainHand();
                        itemInHand.setAmount(itemInHand.getAmount() - 1);
                        player.getInventory().setItemInMainHand(itemInHand);
                        e.setCancelled(true);

                        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Player eventPlayer = e.getPlayer();
                                delayedPlayers.add(eventPlayer);

                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }

                                delayedPlayers.remove(eventPlayer);
                            }
                        });

                    } else {
                    e.getPlayer().sendMessage(Main.getPrefix() + " §cDu darfst diesen Block nicht platzieren!");
                    e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onCanvasBreak(BlockBreakEvent e) {
        if(e.getPlayer() instanceof Player) {
            if(e.getPlayer().getWorld().getName().equals("place")) {
                e.setCancelled(true);
            }
            if(e.getPlayer().getWorld().getName().equals("world") && !(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {

                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void worldChange(PlayerChangedWorldEvent e){
        if (e.getPlayer().getWorld().getName().equals("place")) {
            e.getPlayer().setAllowFlight(true);
            e.getPlayer().setFlying(true);
            e.getPlayer().setFlySpeed(0.3F);
        } else {
            e.getPlayer().setAllowFlight(false);
            e.getPlayer().setFlying(false);
            e.getPlayer().setFlySpeed(0.1F);
        }
    }

    @EventHandler
    public void playerDamageEvent(EntityDamageByEntityEvent e) {
        Player player = (Player) e.getEntity();
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            System.out.println("damage");
            if ((player.getWorld().getName().equals("place"))){
                e.setCancelled(true);
            }
            if ((player.getWorld().getName().equals("world"))){
                e.setCancelled(true);
            }
        }
    }

}
