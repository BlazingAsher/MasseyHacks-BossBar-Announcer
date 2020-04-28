package ca.masseyhacks.bossbarannouncer.util;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import ca.masseyhacks.bossbarannouncer.structures.BossBarMessage;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private BossBarAnnouncer plugin;
    private List<BossBarMessage> messages;

    int currentIndex = 0;

    public MessageManager(BossBarAnnouncer plugin, List<String> messages){
        this.plugin = plugin;
        this.messages = loadMessages(messages);
    }

    private List<BossBarMessage> loadMessages(List<String> messages){
        ArrayList<BossBarMessage> ret = new ArrayList<>();
        for(String messageString : messages){
            String[] messageInfo = messageString.split("!@!");
            String tempMessage = messageInfo[0];
            BarStyle tempStyle = BarStyle.valueOf(messageInfo[1].toUpperCase());
            BarColor tempColor = BarColor.valueOf(messageInfo[2].toUpperCase());
            BossBarMessage temp = new BossBarMessage(tempMessage, tempColor, tempStyle);
            ret.add(temp);

        }
        return ret;
    }

    public void replaceMessages(List<String> messages){
        this.messages = loadMessages(messages);
        currentIndex = 0;
    }

    public BossBarMessage getCurrentMessageObject(){
        return messages.get(currentIndex);
    }

    public String getCurrentMessageString(){
        return messages.get(currentIndex).barMessage;
    }

    public BarColor getCurrentMessageColor(){
        return messages.get(currentIndex).barColor;
    }

    public BarStyle getCurrentMessageStyle(){
        return messages.get(currentIndex).barStyle;
    }

    public void advanceBar(){
        currentIndex = currentIndex + 1 < messages.size() ? currentIndex + 1 : 0;
    }
}
