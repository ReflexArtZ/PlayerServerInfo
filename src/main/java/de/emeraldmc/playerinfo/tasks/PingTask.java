package de.emeraldmc.playerinfo.tasks;

import javafx.concurrent.Task;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PingTask implements Runnable {

    private static final int SIZE = 5;

    private final Map<UUID, TaskHistory> playerHistory = new HashMap<>();

    @Override
    public void run() {
        playerHistory.entrySet().forEach(uuidTaskHistoryEntry -> {
            UUID playerUUID = uuidTaskHistoryEntry.getKey();
            Player player = Bukkit.getPlayer(playerUUID);
            if (player != null) {
                int ping = getPing(player);

                TaskHistory history = uuidTaskHistoryEntry.getValue();
                history.put(ping);
            }
        });
    }

    public void addPlayer(Player player) {
        playerHistory.put(player.getUniqueId(), new TaskHistory(SIZE, getPing(player)));
    }
    public void removePlayer(Player player) {
        playerHistory.remove(player.getUniqueId());
    }
    public int getPing(Player player) {
        return ((CraftPlayer)player).getHandle().ping;
    }
}
