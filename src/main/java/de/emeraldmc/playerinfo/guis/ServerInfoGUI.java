package de.emeraldmc.playerinfo.guis;

import de.emeraldmc.playerinfo.utils.GUIUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class ServerInfoGUI extends AbstractGUI implements Runnable {
    private Player player;

    public ServerInfoGUI(String title, int slots, Player player) {
        super(title, slots);
        addItems();
        this.player = player;
    }

    private void addItems() {
        Map<ItemStack, Integer> entries = GUIUtils.loadServerInfoGuiEntries();
        Set<ItemStack> items = entries.keySet();

        for (ItemStack item : items) {
            setItem(entries.get(item), item, null);
        }
    }

    @Override
    public void run() {
        addItems();
        player.updateInventory();
    }
}
