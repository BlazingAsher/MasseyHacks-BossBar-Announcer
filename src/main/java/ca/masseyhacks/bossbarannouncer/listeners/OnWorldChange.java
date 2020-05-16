package ca.masseyhacks.bossbarannouncer.listeners;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import ca.masseyhacks.bossbarannouncer.util.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class OnWorldChange implements Listener {
    private BossBarAnnouncer plugin;
    public OnWorldChange(BossBarAnnouncer plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();

        if(plugin.getConfig().getStringList("enabledWorlds").contains(player.getWorld().getName())){
            MessageManager temp = plugin.playerBossBars.get(player.getUniqueId());
            if(temp != null){
                temp.bossBar.addPlayer(player);
            }
        }
        else{
            MessageManager temp = plugin.playerBossBars.get(player.getUniqueId());
            if(temp != null){
                temp.bossBar.removePlayer(player);
            }
        }
    }
}
