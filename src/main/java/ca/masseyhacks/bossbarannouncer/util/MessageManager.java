package ca.masseyhacks.bossbarannouncer.util;

import ca.masseyhacks.bossbarannouncer.BossBarAnnouncer;
import ca.masseyhacks.bossbarannouncer.structures.BossBarMessage;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private BossBarAnnouncer plugin;
    private static List<BossBarMessage> messages;
    public final BossBar bossBar;

    int currentIndex = 0;

    public MessageManager(BossBarAnnouncer plugin){
        this.plugin = plugin;
        bossBar = plugin.getServer().createBossBar(getCurrentMessageString(), getCurrentMessageColor(), getCurrentMessageStyle());
        bossBar.setVisible(true);
        bossBar.setProgress(1.0D);
    }

    private static List<BossBarMessage> loadMessages(List<String> messages){
        ArrayList<BossBarMessage> ret = new ArrayList<>();
        for(String messageString : messages){
            String[] messageInfo = splitDataString(messageString);
            String tempMessage = messageInfo[0];
            BarStyle tempStyle = BarStyle.valueOf(messageInfo[1].toUpperCase());
            BarColor tempColor = BarColor.valueOf(messageInfo[2].toUpperCase());
            BossBarMessage temp = new BossBarMessage(tempMessage, tempColor, tempStyle);
            ret.add(temp);

        }
        return ret;
    }

    private static String[] splitDataString(String data){
        String[] ret = new String[3];

        int lastComma = data.lastIndexOf(',');
        ret[2] = data.substring(lastComma + 1);

        int secondComma = data.lastIndexOf(',', lastComma-1);
        ret[1] = data.substring(secondComma+1, lastComma);

        ret[0] = data.substring(0, secondComma);

        return ret;
    }

    public static void replaceMessages(List<String> messages){
        MessageManager.messages = loadMessages(messages);
    }

    public BossBarMessage getCurrentMessageObject(){
        try{
            return messages.get(currentIndex);
        }
        catch(IndexOutOfBoundsException e){
            // Prevent IndexOutOfBounds when messages have been replaced and currentIndex is larger
            return messages.get(0);
        }
    }

    public String getCurrentMessageString(){
        try{
            return messages.get(currentIndex).barMessage;
        }
        catch(IndexOutOfBoundsException e){
            // Prevent IndexOutOfBounds when messages have been replaced and currentIndex is larger
            return messages.get(0).barMessage;
        }

    }

    public BarColor getCurrentMessageColor(){
        try{
            return messages.get(currentIndex).barColor;
        }
        catch(IndexOutOfBoundsException e){
            // Prevent IndexOutOfBounds when messages have been replaced and currentIndex is larger
            return messages.get(0).barColor;
        }

    }

    public BarStyle getCurrentMessageStyle(){
        try{
            return messages.get(currentIndex).barStyle;
        }
        catch(IndexOutOfBoundsException e){
            // Prevent IndexOutOfBounds when messages have been replaced and currentIndex is larger
            return messages.get(0).barStyle;
        }
    }

    public void advanceBar(){
        currentIndex = currentIndex + 1 < messages.size() ? currentIndex + 1 : 0;
    }
}
