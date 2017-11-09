package de.emeraldmc.playerinfo.listener;

import de.emeraldmc.playerinfo.guis.AbstractGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class GUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();

        UUID inventoryUUID = AbstractGUI.openInventories.get(playerUUID);
        if (inventoryUUID != null) {
            e.setCancelled(true);

            AbstractGUI gui = AbstractGUI.getInventoriesByUUID().get(inventoryUUID);
            AbstractGUI.GUIAction action = gui.getActions().get(e.getSlot());

            if (action != null) {
                action.click(player);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Bukkit.getScheduler().cancelAllTasks();
        AbstractGUI.openInventories.remove(playerUUID);
    }
}
