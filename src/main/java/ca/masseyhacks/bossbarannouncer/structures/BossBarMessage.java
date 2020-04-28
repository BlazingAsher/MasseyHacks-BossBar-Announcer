package ca.masseyhacks.bossbarannouncer.structures;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class BossBarMessage {
    public final BarColor barColor;
    public final BarStyle barStyle;
    public final String barMessage;

    public BossBarMessage(String barMessage, BarColor barColor, BarStyle barStyle){
        this.barColor = barColor;
        this.barStyle = barStyle;
        this.barMessage = barMessage;
    }
}
