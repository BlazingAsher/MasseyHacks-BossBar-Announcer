package ca.masseyhacks.bossbarannouncer.commands;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    private BossBarAnnouncer plugin;

    public Reload(BossBarAnnouncer plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("masseyhacks.bossbar.reload")){
            plugin.reloadConfig();
            plugin.messageManager.replaceMessages(plugin.getConfig().getStringList("messages"));
            sender.sendMessage("Reloaded boss bar configuration.");
        }

        return true;
    }
}
