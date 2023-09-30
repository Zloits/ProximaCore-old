package net.terurion.core.listeners;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.ProximaCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Random;

public class OnChat implements Listener {
    private ProximaCore plugin = ProximaCore.getInstance();

    @EventHandler
    public void a(AsyncPlayerChatEvent e) {
        User user = SpaceAPI.getSpigotServer().getUser(e.getPlayer().getUniqueId());

        if (user == null || !SpaceAPI.getSpigotServer().userOnData(e.getPlayer().getUniqueId())) e.setCancelled(true);

        if (e.getMessage().contains("ez") || e.getMessage().contains("e.z") || e.getMessage().contains("easy") || e.getMessage().contains("eazy")) {
            Random random = new Random();
            int num = random.nextInt(5);
            num = num + 1;

            switch (num) {
                case 1:
                    e.setMessage("Do you have admin friends? I want to troll them. Mwhehehe");
                    break;
                case 2:
                    e.setMessage("I like to eat pizza. Do you have a piece of pizza?");
                    break;
                case 3:
                    e.setMessage("Roses are red. Violets are blue. I don't know what i am saying.");
                    break;
                case 4:
                    e.setMessage("Did you see that white thing in the sky? That sun.");
                    break;
                case 5:
                    e.setMessage("Lord thank you for sunshine, thank you for rain, thank you for pain, thank you for joy.");
                    break;
            }
        }

        if (plugin.getConfig().getBoolean("Modules.DefaultChat")) {
            if (user.getRank().getPriority() == 0) {
                e.setFormat(ChatUtil.sendColor("&7&l[" + user.getLevelManager().getLevel() + "✩] &f&7" + user.getNickname() + "&7: " + e.getMessage()));
            }else {
                e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7&l[" + user.getLevelManager().getLevel() + "✩] " + user.getRank().getPrefix() + " &f" + user.getRank().getColor() + user.getNickname() + "&7: &f" + e.getMessage()));
            }
        }
    }
}
