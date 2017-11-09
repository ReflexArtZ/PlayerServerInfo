package de.emeraldmc.playerinfo.utils;

import de.emeraldmc.playerinfo.Main;

public class ConfigUtils {
    private final boolean debug;

    private final String prefix;
    private final String noPermission;


    public ConfigUtils() {
        this.prefix = getString("Config.prefix");
        this.debug = getBoolean("Config.debug");
        this.noPermission = getString("Config.noPermission");
    }

    private String getString(String path) {
        return ChatAPI.translateColor(Main.getInstance().getConfig().getString(path));
    }
    private boolean getBoolean(String path) {
        return Main.getInstance().getConfig().getBoolean(path);
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getNoPermission() {
        return noPermission;
    }
}
