package net.shieldbreak.allvsall;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("§c§lAll§bVS§c§lAll §8§l| §a+ "+e.getPlayer().getName());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("§c§lAll§bVS§c§lAll §8§l| §c- "+e.getPlayer().getName());
    }

}
