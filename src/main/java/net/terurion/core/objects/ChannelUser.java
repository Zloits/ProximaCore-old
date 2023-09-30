package net.terurion.core.objects;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.ProximaCore;
import net.terurion.core.adapters.CUserAdapter;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ChannelUser implements CUserAdapter {
    private ProximaCore plugin = ProximaCore.getInstance();

    private UUID uuid;
    private String displayName;

    public ChannelUser(UUID uuid, String displayName) {
        this.uuid = uuid;
        this.displayName = displayName;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public User getUser() {
        User user = SpaceAPI.getSpigotServer().getUser(getUUID());

        if (user == null) return null;

        return user;
    }

    @Override
    public boolean isOnline() {
        Connection connection = SpaceAPI.getServerConnection();
        String sql = "";

        if (connection == null) return false;

        try {
            sql = "select online from users where uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, getUUID().toString());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean("online")) {
                    return true;
                } return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public void sendMessage(String message) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Message");
        dataOutput.writeUTF(displayName);
        dataOutput.writeUTF(ChatUtil.sendColor(message));

        Bukkit.getServer().sendPluginMessage(plugin, "BungeeCord", dataOutput.toByteArray());
    }

    @Override
    public void send(String serverName) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(serverName);

        getUser().playerReturn().sendPluginMessage(plugin, "BungeeCord", dataOutput.toByteArray());
    }
}
