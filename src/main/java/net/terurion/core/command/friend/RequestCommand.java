package net.terurion.core.command.friend;

import net.terurion.api.manager.FriendManager;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SubCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RequestCommand extends SubCommand {

    public RequestCommand() {
        super("requests");
    }

    @Override
    public boolean run(User user, String[] args) {
        FriendManager friendManager = user.getFriendManager();

        Player player = user.playerReturn();

        if (friendManager.getPendings() == null || friendManager.getPendings().isEmpty()) {
            player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
            player.sendMessage(ChatUtil.sendColor("&a Request List &2(&f0&2/&f10&2)"));
            player.sendMessage(ChatUtil.sendColor("&f &a"));
            player.sendMessage(ChatUtil.sendColor("&c You have no requests."));
            player.sendMessage(ChatUtil.sendColor("&f &a"));
            player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
            return true;
        }

        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
        player.sendMessage(ChatUtil.sendColor("&a Request List &2(&f" + user.getFriendManager().getFriends().size() + "&2/&f10&2)"));
        player.sendMessage(ChatUtil.sendColor("&f &a"));
        for (User friend : friendManager.getPendings()) {
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
