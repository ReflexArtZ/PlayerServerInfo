package de.emeraldmc.playerinfo.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AbstractGUI {
    public static Map<UUID, AbstractGUI> inventoriesByUUID = new HashMap<>();
    public static Map<UUID, UUID> openInventories = new HashMap<>();

    private UUID uuid;
    private Inventory inventory;
    private HashMap<Integer, GUIAction> actions;


    public AbstractGUI(String title, int slots)  {
        uuid = UUID.randomUUID();
        inventory = Bukkit.createInventory(null, slots, title);
        actions = new HashMap<>();
        inventoriesByUUID.put(getUuid(), this);
    }

    public static Map<UUID, AbstractGUI> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<Integer, GUIAction> getActions() {
        return actions;
    }

    public void setItem(int slot, ItemStack stack, @Nullable GUIAction action) {
        inventory.setItem(slot, stack);
        if (action != null) {
            actions.put(slot, action);
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public void open(Player player) {
        player.openInventory(inventory);
        openInventories.put(player.getUniqueId(), getUuid());
    }


    public interface GUIAction {
        void click(Player player);
    }
}
