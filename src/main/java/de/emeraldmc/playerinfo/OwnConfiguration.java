package de.emeraldmc.playerinfo;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OwnConfiguration {
    private File configFile;
    private FileConfiguration configConfiguration;

    public OwnConfiguration(String name) {
        createFiles(name);
    }

    /**
     * Create independent configuration
     * @param name configuration-file name
     */
    private void createFiles(String name) {
        configFile = new File(Main.getInstance().getDataFolder(), name);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            Main.getInstance().saveResource(name, false);
        }

        configConfiguration = new YamlConfiguration();
        try {
            configConfiguration.load(configFile);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfigConfiguration() {
        return configConfiguration;
    }

    public FileConfiguration getConfig() {
        return getConfigConfiguration();
    }
}
