package net.terurion.core.objects.messaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessageChannel implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;

        ByteArrayDataInput dataInput = ByteStreams.newDataInput(message);

        String subChannel = dataInput.readUTF();
    }
}
