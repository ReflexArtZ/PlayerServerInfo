package de.emeraldmc.playerinfo.utils;

import de.emeraldmc.playerinfo.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ChatAPI {

    /**
     * Translates Colorcode
     * @param s String with old Colorcode
     * @return Translated String
     */
    public static String translateColor(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

    public static List<String> translateListColor(List<String> list, @Nullable Player player) {
        List<String> translatedList = new ArrayList<>();
        for (String s : list) {
            translatedList.add(PlaceholderAPI.setPlaceholders(player, translateColor(s)));
        }
        return translatedList;
    }

    /**
     * Send Message with prefix to a Player
     * @param p Player
     * @param msg Message
     */
    public static void sendMessage(Player p, String msg) {
        p.sendMessage(Main.getInstance().configuration.getPrefix()+translateColor(msg));
    }

    /**
     * Send Message with prefix to Console
     * @param sender ConsoleSender
     * @param msg Message
     */
    public static void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(Main.getInstance().configuration.getPrefix()+translateColor(msg));
    }

    public static void sendNoPermissionMessage(Player player) {
        sendMessage(player, Main.getInstance().configuration.getNoPermission());
    }
}
