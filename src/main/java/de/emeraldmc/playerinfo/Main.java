package de.emeraldmc.playerinfo;

import de.emeraldmc.playerinfo.commands.PlayerInfoGUICommand;
import de.emeraldmc.playerinfo.commands.ServerInfoGUICommand;
import de.emeraldmc.playerinfo.listener.GUIListener;
import de.emeraldmc.playerinfo.listener.JoinListener;
import de.emeraldmc.playerinfo.listener.QuitListener;
import de.emeraldmc.playerinfo.tasks.PingTask;
import de.emeraldmc.playerinfo.tasks.TPSTask;
import de.emeraldmc.playerinfo.utils.ConfigUtils;
import de.emeraldmc.playerinfo.utils.Debug;
import de.emeraldmc.playerinfo.utils.Placeholders;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private static Main instance;

    public ConfigUtils configuration;

    public OwnConfiguration serverGUIConfiguration;
    public OwnConfiguration playerGUIConfiguration;

    private CPULoad cpuLoad;
    private SystemInfos systemInfos;

    public Map<Player, String> playerCountryMap = new HashMap<>();

    private TPSTask tpsTask;

    public TPSTask getTpsTask() {
        return tpsTask;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        initializeConfig();

        configuration = new ConfigUtils();

        Bukkit.getConsoleSender().sendMessage(configuration.getPrefix()+"§7INFO - Plugin §aSuccessfully §7initialized!");
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(configuration.getPrefix()+"§cYou need PlaceholderAPI for this plugin to work!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        new Placeholders(this).hook();
        serverGUIConfiguration = new OwnConfiguration("ServerGUI.yml");
        playerGUIConfiguration = new OwnConfiguration("PlayerGUI.yml");

        registerEvents();
        registerCommands();

        cpuLoad = new CPULoad();
        systemInfos = new SystemInfos();
        initializeTasks();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getScheduler().cancelAllTasks();
    }

    /**
     * Loading standart config
     */
    private void initializeConfig() {
        File f = new File(getDataFolder()+ File.separator+ "config.yml");
        if(!f.exists())
        {
            this.saveDefaultConfig();
    }
        this.reloadConfig();
    }

    /**
     * Starting tasks
     */
    private void initializeTasks() {
        tpsTask = new TPSTask();
        Bukkit.getServer().getScheduler().runTaskTimer(this, tpsTask, 20L, 20L);
        Debug.print("Initialized TPSTask");
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Debug.print("Registered GUIListener");
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Debug.print("Registered QuitListener");
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Debug.print("Registered JoinListener");
    }

    private void registerCommands() {
        getCommand("serverinfo").setExecutor(new ServerInfoGUICommand());
        Debug.print("Registered ServerInfo-Command");
        getCommand("playerinfo").setExecutor(new PlayerInfoGUICommand());
        Debug.print("Registered PlayerInfo-Command");
    }

    public CPULoad getCpuLoad() {
        return cpuLoad;
    }

    public SystemInfos getSystemInfos() {
        return systemInfos;
    }
}
