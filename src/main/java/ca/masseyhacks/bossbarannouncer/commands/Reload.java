package ca.masseyhacks.bossbarannouncer.commands;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import ca.masseyhacks.bossbarannouncer.util.MessageManager;
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
        if(args.length > 0 && args[0].equals("reload") && sender.hasPermission("masseyhacks.bossbar.reload")){
            plugin.reloadConfig();
            MessageManager.replaceMessages(plugin.getConfig().getStringList("messages"));
            sender.sendMessage("Reloaded boss bar configuration.");
        }

        else{
            sender.sendMessage("BossBarAnnouncer version " + plugin.getDescription().getVersion() + "");
            sender.sendMessage("Registered BossBars: " + plugin.playerBossBars.size());
        }

        return true;
    }
}
