package ca.masseyhacks.bossbarannouncer.listeners;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnConnect implements Listener {
    private BossBarAnnouncer plugin;
    public OnConnect(BossBarAnnouncer plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event){
        Player player = event.getPlayer();

        plugin.bossBar.addPlayer(player);
    }
}
