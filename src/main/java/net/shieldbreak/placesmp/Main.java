package net.shieldbreak.placesmp;

import net.shieldbreak.placesmp.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    private static Main instance;
    public static String prefix = "§c§lr/place§f§lCH §8» ";

    @Override
    public void onEnable() {
        instance = this;

        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("web").setExecutor(new WebCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("credits").setExecutor(new CreditsCommand());
        getCommand("place").setExecutor(new PlaceCommand());
        getCommand("smp").setExecutor(new SmpCommand());
        getCommand("canvas").setExecutor(new MapCommand());
        getCommand("rtp").setExecutor(new RtpCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Main getInstance() {
        return instance;
    }
}

