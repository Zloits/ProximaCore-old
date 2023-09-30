package net.terurion.core.command.friend;

import net.terurion.api.manager.FriendManager;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FriendListCommand extends SingleCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public FriendListCommand() {
        super("friends", "fl", "friendlist");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!plugin.getConfig().getBoolean("Modules.FriendCommands")) {
            user.sendMessage("&cSorry, but friend isn't available for this server.");
            return false;
        }

        FriendManager friendManager = user.getFriendManager();

        Player player = user.playerReturn();
        if (friendManager.getFriends() == null || friendManager.getFriends().isEmpty()) {
            player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂ &f"));
            player.sendMessage(ChatUtil.sendColor("&a Friend List &2(&f0&2/&f10&2)"));
            player.sendMessage(ChatUtil.sendColor("&f &a &f"));
            player.sendMessage(ChatUtil.sendColor("&c You have no friends yet."));
            player.sendMessage(ChatUtil.sendColor("&f &a"));
            player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
            return true;
        }

        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂ &f"));
        player.sendMessage(ChatUtil.sendColor("&a Friend List &2(&f" + user.getFriendManager().getFriends().size() + "&2/&f10&2)"));
        player.sendMessage(ChatUtil.sendColor("&f &a &f"));
        for (User friend : friendManager.getFriends()) {
            player.sendMessage(ChatUtil.sendColor("&&8 - &7" + friend.getNickname()));
        }
        player.sendMessage(ChatUtil.sendColor("&f &a"));
        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
