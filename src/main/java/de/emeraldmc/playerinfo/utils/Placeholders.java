package de.emeraldmc.playerinfo.utils;

import de.emeraldmc.playerinfo.Main;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Placeholders extends EZPlaceholderHook {

    public Placeholders(Plugin plugin) {
        super(plugin, "info");
    }

    public String onPlaceholderRequest(Player player, String s) {
        if (s.equals("tps_minute")) return String.valueOf(Main.getInstance().getTpsTask().getMinuteTPS());
        if (s.equals("tps_quarter")) return String.valueOf(Main.getInstance().getTpsTask().getQuarterTPS());
        if (s.equals("tps_half")) return String.valueOf(Main.getInstance().getTpsTask().getHalfTPS());
        if (s.equals("cpu_load_process")) return String.valueOf(Main.getInstance().getCpuLoad().getProcessCPULoad());
        if (s.equals("cpu_load_system")) return String.valueOf(Main.getInstance().getCpuLoad().getCPULoad());

        if (s.equals("os_name")) return Main.getInstance().getSystemInfos().getOsName();
        if (s.equals("os_version")) return Main.getInstance().getSystemInfos().getOsVersion();
        if (s.equals("os_arch")) return Main.getInstance().getSystemInfos().getOsArch();
        if (s.equals("cpu_cores")) return String.valueOf(Main.getInstance().getSystemInfos().getCores());
        if (s.equals("cpu_name")) return Main.getInstance().getSystemInfos().getCpuName();
        if (s.equals("cpu_load_average")) return String.valueOf(Main.getInstance().getSystemInfos().getAverageLoad()*100);
        if (s.equals("server_worlds")) return String.valueOf(Bukkit.getWorlds().size());
        if (s.equals("server_plugins")) return String.valueOf(Bukkit.getPluginManager().getPlugins().length);
        if (s.equals("server_motd")) return Bukkit.getServer().getMotd();
        if (s.equals("server_version")) return Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
        if (s.equals("player_country")) return Main.getInstance().playerCountryMap.get(player);
        if (player == null) return "";
        return null;
    }
}
