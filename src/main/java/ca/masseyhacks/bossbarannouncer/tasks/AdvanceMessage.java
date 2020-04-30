package ca.masseyhacks.bossbarannouncer.tasks;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import ca.masseyhacks.bossbarannouncer.structures.BossBarMessage;
import ca.masseyhacks.bossbarannouncer.util.MessageManager;
import org.bukkit.scheduler.BukkitRunnable;


public class AdvanceMessage extends BukkitRunnable {
    private final BossBarAnnouncer plugin;

    public AdvanceMessage(BossBarAnnouncer plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // Go through all message managers and advance those that have players attached to them
        for(MessageManager temp : plugin.playerBossBars.values()){
            if(temp.bossBar.getPlayers().size() > 0){
                temp.advanceBar();

                BossBarMessage currentMessage = temp.getCurrentMessageObject();
                temp.bossBar.setTitle(currentMessage.barMessage);
                temp.bossBar.setColor(currentMessage.barColor);
                temp.bossBar.setStyle(currentMessage.barStyle);
            }
        }
    }
}
