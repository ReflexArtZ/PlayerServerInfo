package de.emeraldmc.playerinfo.listener;

import de.emeraldmc.playerinfo.Main;
import de.emeraldmc.playerinfo.guis.AbstractGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        AbstractGUI.openInventories.remove(playerUUID);
        Main.getInstance().playerCountryMap.remove(e.getPlayer());
    }
}
