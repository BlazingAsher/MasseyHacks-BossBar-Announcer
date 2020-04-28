package ca.masseyhacks.bossbarannouncer.listeners;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnDisconnect implements Listener {
    private BossBarAnnouncer plugin;
    public OnDisconnect(BossBarAnnouncer plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void OnDisconnect(PlayerQuitEvent event){
        Player player = event.getPlayer();

        plugin.bossBar.removePlayer(player);
    }
}
