package de.emeraldmc.playerinfo.utils;

import de.emeraldmc.playerinfo.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIUtils {
    public static Map<ItemStack, Integer> loadServerInfoGuiEntries() {
        return loadInventoryEntries(Main.getInstance().serverGUIConfiguration.getConfig(), null);
    }
    public static Map<ItemStack, Integer> loadPlayerInfoGuiEntries(Player player) {
        return loadInventoryEntries(Main.getInstance().playerGUIConfiguration.getConfig(), player);
    }

    /**
     * loads the inventory entries from the config file
     * @param configuration the configuration
     * @param player
     * @return
     */
    private static Map<ItemStack, Integer> loadInventoryEntries(FileConfiguration configuration, @Nullable Player player) {
        Object[] objects = configuration.getConfigurationSection("Menu").getKeys(false).toArray();
        int j = objects.length;

        Map<ItemStack, Integer> entries = new HashMap<>();
        for (int i = 0; i < j; i++) {
            Object currentValues = objects[i];
            if (((String) currentValues).equalsIgnoreCase("slots")) continue;
            if (((String) currentValues).equalsIgnoreCase("title")) continue;
            Material material = Material.getMaterial(configuration.getString("Menu."+currentValues+".material"));
            String name = ChatAPI.translateColor(configuration.getString("Menu."+currentValues+".name"));
            List<String> lore = ChatAPI.translateListColor(configuration.getStringList("Menu." + currentValues + ".lore"), player);
            int slot = configuration.getInt("Menu."+currentValues+".slot")-1;
            entries.put(createItem(material, name, lore), slot);
        }

        return entries;
    }
    public static ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack stack = new ItemStack(material, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        if (lore != null) meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }
    public static int calcPlayerSlots() {
        int players = Bukkit.getOnlinePlayers().size();
        int slots = players;
        if (players <= 54) {
            while (slots %9 != 0) {
                slots++;
            }
            return slots;
        }
        return 54;
    }
}
