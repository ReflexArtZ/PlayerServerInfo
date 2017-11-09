package de.emeraldmc.playerinfo.utils;

import de.emeraldmc.playerinfo.Main;
import org.bukkit.Bukkit;

public class Debug {
    public static void print(String s) {
        if (Main.getInstance().configuration.isDebug()) {
            Bukkit.getConsoleSender().sendMessage(Main.getInstance().configuration.getPrefix()+s);
        }
    }
}
