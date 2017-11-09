package de.emeraldmc.playerinfo.commands;

import de.emeraldmc.playerinfo.Main;
import de.emeraldmc.playerinfo.guis.PlayerGUI;
import de.emeraldmc.playerinfo.utils.ChatAPI;
import de.emeraldmc.playerinfo.utils.GUIUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfoGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("playerinfo")) {
            if (!(sender instanceof Player)) return true;

            Player player = (Player)sender;

            if (!player.hasPermission("info.playerinfo")) {
                ChatAPI.sendNoPermissionMessage(player);
                return true;
            }

            PlayerGUI playerGUI =  new PlayerGUI(ChatAPI.translateColor(Main.getInstance().playerGUIConfiguration.getConfig().getString("Menu.title")), GUIUtils.calcPlayerSlots(), 1);
            playerGUI.open(player);
        }
        return false;
    }
}
