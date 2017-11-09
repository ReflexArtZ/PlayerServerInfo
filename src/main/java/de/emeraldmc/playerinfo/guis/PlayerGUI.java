package de.emeraldmc.playerinfo.guis;

import de.emeraldmc.playerinfo.Main;
import de.emeraldmc.playerinfo.utils.GUIUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerGUI extends AbstractGUI {

    private int site;
    private final Player[] players;
    private final Player[] currPlayers;

    public PlayerGUI(String title, int slots, int site) {
        super(title, slots);
        this.site = site;
        this.players = players();
        currPlayers = calcSitePlayers();
        if (Bukkit.getOnlinePlayers().size() > 45) {
            if (site != 1) {
                setItem(45, GUIUtils.createItem(Material.ARROW, "Zurück", null), player -> {
                    player.closeInventory();
                    new PlayerGUI(String.valueOf(site-1), 54, site - 1).open(player);
                });
            }
            setItem(53, GUIUtils.createItem(Material.ARROW, "Vor", null), player -> {
                player.closeInventory();
                new PlayerGUI(String.valueOf(site+1), 54, site+1).open(player);
            });
        }
        setItems();
    }

    private void setItems() {
        int cnt = 0;
        for (Player target : currPlayers) {
            if (target == null) continue;
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

            skullMeta.setDisplayName(target.getName());
            List<String> lore = new ArrayList<>();
            skullMeta.setOwner(target.getName());
            lore.add("UUID: "+target.getUniqueId());
            skullMeta.setLore(lore);
            itemStack.setItemMeta(skullMeta);
            setItem(cnt, itemStack, player -> {
                player.closeInventory();
                new PlayerInfoGUI("§8PlayerInfo §7- §b"+target.getName(), Main.getInstance().playerGUIConfiguration.getConfig().getInt("Menu.slots"), target).open(player);
            });
            cnt++;
        }
    }

    private static Player[] players() {
        Player[] players = new Player[Bukkit.getOnlinePlayers().size()];
        int cnt = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            players[cnt] = p;
            cnt++;
        }
        return players;
    }

    /**
     * Calculates the needed amount of GUI sites
     * @return gui sites
     */
    private Player[] calcSitePlayers() {
        Player[] players = new Player[45];
        int start = site*45-45;
        int end = site*45;
        for (int i = start; i < end+1; i++) {
            try {
                players[i] = this.players[i];
            } catch (Exception ex) {
                continue;
            }
        }
        return players;
    }
}
