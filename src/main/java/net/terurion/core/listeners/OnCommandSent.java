package net.terurion.core.listeners;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.core.ProximaCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class OnCommandSent implements Listener {
    private ProximaCore plugin = ProximaCore.getInstance();

    @EventHandler
    public void a(PlayerCommandSendEvent e) {
        User user = SpaceAPI.getSpigotServer().getUser(e.getPlayer().getUniqueId());

        if (!user.isDev()) {
            for (String str : plugin.getConfig().getStringList("Remove-TabCompleter")) {
                e.getCommands().remove(str);
            }
        }
    }
}
