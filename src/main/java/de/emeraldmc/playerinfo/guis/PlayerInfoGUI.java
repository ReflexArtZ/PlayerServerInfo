package de.emeraldmc.playerinfo.guis;

import de.emeraldmc.playerinfo.utils.GUIUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class PlayerInfoGUI extends AbstractGUI {

    private Player player;

    public PlayerInfoGUI(String title, int slots, Player player) {
        super(title, slots);

        this.player = player;

        addItems();
    }

    private void addItems() {
        Map<ItemStack, Integer> entries = GUIUtils.loadPlayerInfoGuiEntries(player);
        Set<ItemStack> items = entries.keySet();

        for (ItemStack item : items) {
            setItem(entries.get(item), item, null);
        }
    }


}
