package net.terurion.core.objects;

import net.terurion.api.SpaceAPI;
import net.terurion.api.type.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class RankPermission {
    private Rank rank;
    private Collection<String> perms;

    public RankPermission(Rank rank, Collection<String> perms) {
        this.rank = rank;
        this.perms = perms;
    }

    public Collection<String> getPerms() {
        return perms;
    }

    private void insertNew(String string) {
        Connection connection = SpaceAPI.getServerConnection();
        String sql = "";

        if (connection == null) return;

        try {
            sql = "insert ignore into perms (rank, perms) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, rank.getPriority());
            statement.setString(2, string);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete(String string) {
        Connection connection = SpaceAPI.getServerConnection();
        String sql = "";

        if (connection == null) return;

        try {
            sql = "delete from perms where perms = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, string);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPermission(String permission) {
        if (getPerms().contains(permission)) return;

        insertNew(permission);
        getPerms().add(permission);
    }

    public void removePermission(String permission) {
        if (!getPerms().contains(permission)) return;

        delete(permission);
        getPerms().remove(permission);
    }

    public Rank getParentRank() {
        return rank;
    }
}
