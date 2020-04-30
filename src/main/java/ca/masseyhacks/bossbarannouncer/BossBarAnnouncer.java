package ca.masseyhacks.bossbarannouncer;

import ca.masseyhacks.bossbarannouncer.commands.Reload;
import ca.masseyhacks.bossbarannouncer.listeners.OnConnect;
import ca.masseyhacks.bossbarannouncer.listeners.OnDisconnect;
import ca.masseyhacks.bossbarannouncer.listeners.OnWorldChange;
import ca.masseyhacks.bossbarannouncer.tasks.AdvanceMessage;
import ca.masseyhacks.bossbarannouncer.util.MessageManager;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class BossBarAnnouncer extends JavaPlugin {

    //public BossBar bossBar;
    //public MessageManager messageManager;
    private BukkitRunnable bgAdvanceBar;
    public HashMap<UUID, MessageManager> playerBossBars;

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileConfiguration config = this.getConfig();

        ArrayList<String> defaultValues = new ArrayList<>();
        defaultValues.add("Test,SOLID,WHITE");

        // Default config values
        config.addDefault("messages", defaultValues);
        config.addDefault("messageTime", 100);
        config.addDefault("enabledWorlds", new ArrayList<String>());

        config.options().copyDefaults(true);
        saveConfig();

        playerBossBars = new HashMap<>();

        // Load BossBar messages
        MessageManager.replaceMessages(config.getStringList("messages"));

        //messageManager = new MessageManager(this, config.getStringList("messages"));

        //bossBar = getServer().createBossBar(messageManager.getCurrentMessageString(), messageManager.getCurrentMessageColor(), messageManager.getCurrentMessageStyle());
        //bossBar.setVisible(true);
        //bossBar.setProgress(1.0D);

        // Register commands
        getLogger().info("Registering commands.");
        getCommand("mhbossbar").setExecutor(new Reload(this));

        // Register event listeners
        getLogger().info("Registering event handlers.");
        getServer().getPluginManager().registerEvents(new OnConnect(this), this);
        getServer().getPluginManager().registerEvents(new OnDisconnect(this), this);
        getServer().getPluginManager().registerEvents(new OnWorldChange(this), this);

        // Register background tasks
        getLogger().info("Registering background tasks.");
        bgAdvanceBar = new AdvanceMessage(this);
        bgAdvanceBar.runTaskTimer(this, 0, config.getInt("messageTime"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        bgAdvanceBar.cancel();
    }
}
