package ca.masseyhacks.bossbarannouncer.tasks;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import ca.masseyhacks.bossbarannouncer.structures.BossBarMessage;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;
import java.util.UUID;

public class AdvanceMessage extends BukkitRunnable {
    private final BossBarAnnouncer plugin;

    public AdvanceMessage(BossBarAnnouncer plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        //System.out.println("Running cleanup task");
        // cleans up the confirm tokens that are older than 30 seconds
        plugin.messageManager.advanceBar();

        BossBarMessage currentMessage = plugin.messageManager.getCurrentMessageObject();
        plugin.bossBar.setTitle(ChatColor.translateAlternateColorCodes('&', currentMessage.barMessage));
        plugin.bossBar.setColor(currentMessage.barColor);
        plugin.bossBar.setStyle(currentMessage.barStyle);
    }
}
