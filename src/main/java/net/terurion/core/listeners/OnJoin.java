package net.terurion.core.listeners;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.ProximaCore;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.SQLException;
import java.util.*;
public class OnJoin implements Listener {
    private ProximaCore plugin = ProximaCore.getInstance();

    @EventHandler
    public void a(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        User user = SpaceAPI.getSpigotServer().getUser(uuid);

        Player player = e.getPlayer();

        if (user == null || !SpaceAPI.getSpigotServer().userOnData(uuid)) {
            try {
                SpaceAPI.getSpigotServer().addUser(uuid);

                player.sendMessage(ChatUtil.sendColor(user.getNickname() + ", You're profile has been created."));
                player.sendMessage(ChatUtil.sendColor("&7Your Profile UUID: &8" + user.getUUID().toString()));
                player.sendMessage(ChatUtil.sendColor("&a&lTIPS: &fDo not give your privacy information. This server is have a bad security, we're still working on it."));
            }catch (SQLException exception) {
                exception.printStackTrace();
                e.getPlayer().sendMessage(ChatColor.RED + "You're profile can't be created. Please contact Core Development Team.");
            }
        }

        if (plugin.getConfig().getBoolean("Modules.LobbyProtection")) {
            player.setGameMode(GameMode.ADVENTURE);
        }

        PermissionAttachment attachment = player.addAttachment(plugin);
        plugin.getPermissionManager().getPerms().put(player.getUniqueId(), attachment);
        setPlayerPermission(user);

        player.updateCommands();

        if (plugin.getConfig().getBoolean("Modules.JoinMessage")) {
            if (user.getRank().getPriority() > 10) {
                e.setJoinMessage(ChatUtil.sendColor("&9>&4>&9> " + user.getRank().getPrefix() + " " + user.getNickname() + " &4has joined the lobby! &9<&4<&9<"));
            } else if (user.getRank().getPriority() > 3) {
                e.setJoinMessage(ChatUtil.sendColor(user.getRank().getPrefix() + " " + user.getNickname() + " &dhas joined the lobby!"));
            } else {
                e.setJoinMessage("");
            }

            if (user.isDev()) {
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
                        user.sendMessage(moduleName + ": " + onOrOff);
                    }
                }
            }

            player.sendMessage(ChatUtil.sendColor("&bYou are playing on profile&7: &8" + user.getUUID().toString()));
            player.setLevel(user.getLevelManager().getLevel());

            String tipsPrefix = "&a&lTIPS: &f";
            Random random = new Random();
            int a = random.nextInt(5);
            a = a + 1;
            switch (a) {
                case 1:
                    user.sendMessage(tipsPrefix + "Make sure that your account is safe. If you have any problems about your account, please contact to our Core Development Team, or Staffs");
                    break;
                case 2:
                    user.sendMessage(tipsPrefix + "Do not give your sensitive informations.");
                    break;
                case 3:
                    if (plugin.getConfig().getBoolean("Modules.TrollCommands")) {
                        user.sendMessage(tipsPrefix + "Did you know? That the server have a secret commands? I'll give you 1 secret command: &d/freegmctrustme");
                    } else user.sendMessage(tipsPrefix + "Do not give your privacy information. This server is have a bad security, we're still working on it.");
                    break;
                case 4:
                    user.sendMessage(tipsPrefix + "&c&lHACKING &fwill be banned from the server.");
                    break;
                case 5:
                    user.sendMessage(tipsPrefix + "Always follow our rules. Make sure that you read the rules!");
                    break;
            }
        }else {
            e.setJoinMessage("");
        }
    }

    @EventHandler
    public void b(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

    private void setPlayerPermission(User user) {
        PermissionAttachment attachment = plugin.getPermissionManager().getPerms().get(user.getUUID());

        for (String permission : plugin.getPermissionManager().getPermission(user.getRank()).getPerms()) {
            attachment.setPermission(permission, true);
        }

        attachment.getPermissible().recalculatePermissions();
    }
}
