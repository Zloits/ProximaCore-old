package net.terurion.core.managers;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.type.Rank;
import net.terurion.core.ProximaCore;
import net.terurion.core.objects.RankPermission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PermissionManager {
    private ProximaCore plugin = ProximaCore.getInstance();

    private HashMap<UUID, PermissionAttachment> perms;
    private Collection<RankPermission> rankPerms;

    private void log(String string) {
       plugin.getLogger().info(string);
    }

    public PermissionManager() {
        perms = new HashMap<>();
        rankPerms = new ArrayList<>();
        log("Initializing for Rank Permission.");

        for (Rank rank : Rank.values()) {
            log("Load for " + rank.getDefaultName() + " permissions...");
            Connection connection = SpaceAPI.getServerConnection();
            String sql = "";

            if (connection == null) return;

            try {
                sql = "select * from perms where rank = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, rank.getPriority());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Collection<String> permissionsRank = new ArrayList<>();
                    permissionsRank.add(resultSet.getString("perms"));

                    for (String perms2 : permissionsRank) {
                        log("Permission: " + perms2 + " is loaded.");
                    }

                    rankPerms.add(new RankPermission(rank, permissionsRank));
                    log("Load for " + rank.getDefaultName() + " is done.");
                }

                rankPerms.add(new RankPermission(rank,new ArrayList<>()));
                log("No permissions to load.");
                log("Load for " + rank.getDefaultName() + " is done.");
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadPermission(Rank rank) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = SpaceAPI.getSpigotServer().getUser(player.getUniqueId());

            if (user.getRank() != rank) return;

            PermissionAttachment attachment = perms.get(player.getUniqueId()  );
            player.removeAttachment(attachment);

            PermissionAttachment newAttachment = player.addAttachment(plugin);

            for (String permission : getPermission(user.getRank()).getPerms()) {
                newAttachment.setPermission(permission, true);
            }

            perms.put(player.getUniqueId(), newAttachment);
            player.updateCommands();
            newAttachment.getPermissible().recalculatePermissions();
        }
    }

    public HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }

    public Collection<RankPermission> getRankPerms() {
        return rankPerms;
    }

    public RankPermission getPermission(Rank rank) {
        for (RankPermission rankPermission : getRankPerms()) {
            if (rankPermission.getParentRank() == rank) {
                return rankPermission;
            }
        } return null;
    }
}
