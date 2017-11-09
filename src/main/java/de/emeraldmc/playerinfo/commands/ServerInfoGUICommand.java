package de.emeraldmc.playerinfo.commands;

        import de.emeraldmc.playerinfo.Main;
        import de.emeraldmc.playerinfo.guis.ServerInfoGUI;
        import de.emeraldmc.playerinfo.utils.ChatAPI;
        import org.bukkit.Bukkit;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;
        import org.bukkit.entity.Player;

public class ServerInfoGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("serverinfo")) {
            if (!(sender instanceof Player)) return true;

            Player player = (Player)sender;

            if (!player.hasPermission("info.serverinfo")) {
                ChatAPI.sendNoPermissionMessage(player);
                return true;
            }

            ServerInfoGUI serverInfoGUI =  new ServerInfoGUI(ChatAPI.translateColor(Main.getInstance().serverGUIConfiguration.getConfig().getString("Menu.title")), Main.getInstance().serverGUIConfiguration.getConfig().getInt("Menu.slots"), (Player)sender);
            serverInfoGUI.open(player);
            Bukkit.getScheduler().runTaskTimer(Main.getInstance(), serverInfoGUI, 20L, 20L);
        }
        return false;
    }
}
