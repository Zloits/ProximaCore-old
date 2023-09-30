package net.terurion.core.managers;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.core.objects.ChannelUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ChannelUserManager {
    private Collection<ChannelUser> registerChannelUsers = new ArrayList<>();

    public ChannelUserManager() {
        loadGlobalUser();
    }

    public void loadGlobalUser() {
        Connection connection = SpaceAPI.getServerConnection();
        String sql = "";

        if (connection == null) return;

        try {
            sql = "select * from users";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                User user = SpaceAPI.getSpigotServer().getUser(uuid);

                String displayName = user.getMinecraftName();

                registerChannelUsers.add(new ChannelUser(uuid, displayName));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<ChannelUser> getRegisterChannelUsers() {
        return registerChannelUsers;
    }

    public ChannelUser getChannelUser(UUID uuid) {
        for (ChannelUser channelUser : getRegisterChannelUsers()) {
            if (channelUser.getUUID() == uuid) {
                return channelUser;
            }
        } return null;
    }
}
