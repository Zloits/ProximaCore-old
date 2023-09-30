package net.terurion.core.command;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ReloadChangesCommand extends SingleCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public ReloadChangesCommand() {
        super("reloadchanges");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isDev()) {
            user.sendMessage("&cSorry, to use this command you have to be &6&lDEVELOPER");
            return false;
        }

        Plugin plugin = Bukkit.getPluginManager().getPlugin("ProximaCore");

        plugin.saveConfig();
        plugin.reloadConfig();
        user.sendMessage("&aChanges has been saved.");

        for (Player devPlayers : Bukkit.getOnlinePlayers()) {
            User devUsers = SpaceAPI.getSpigotServer().getUser(devPlayers.getUniqueId());

            if (devUsers.isDev()) {
                boolean joinMessage = plugin.getConfig().getBoolean("Modules.JoinMessage");
                boolean trollModules = plugin.getConfig().getBoolean("Modules.TrollCommands");
                boolean guildCommands = plugin.getConfig().getBoolean("Modules.GuildCommands");
                boolean friendCommands = plugin.getConfig().getBoolean("Modules.FriendCommands");
                boolean lobbyProtection = plugin.getConfig().getBoolean("Modules.LobbyProtection");

                HashMap<String, Boolean> modules = new HashMap<>();
                modules.put("Join Message", joinMessage);
                modules.put("Troll Modules", trollModules);
                modules.put("Guild Modules", guildCommands);
                modules.put("Friend MOdules", friendCommands);
                modules.put("Lobby Protection", lobbyProtection);

                for (String moduleName : modules.keySet()) {
                    boolean get = modules.get(moduleName);
                    String onOrOff;

                    if (!get) {
                        onOrOff = "&c&lOFF";
                        devPlayers.sendMessage(moduleName + ": " + onOrOff);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
