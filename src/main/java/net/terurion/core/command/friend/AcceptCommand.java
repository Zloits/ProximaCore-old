package net.terurion.core.command.friend;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.Friend;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AcceptCommand extends SubCommand {

    public AcceptCommand() {
        super("accept");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();

        if (args.length < 2) {
            player.sendMessage(ChatUtil.sendColor("&cWrong syntax, to use this command you have to insert User's"));
            return false;
        }

        if (args.length > 2) {
            player.sendMessage(ChatUtil.sendColor("&cWrong syntax, to use this command you have to insert User's"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        User requester = SpaceAPI.getSpigotServer().getUser(target.getUniqueId());

        if (requester == null) {
            player.sendMessage(ChatUtil.sendColor("&cSorry, but we can't find any user with that name. Or this user has never joined this server before."));
            return false;
        }

        Friend friend = user.getFriend(requester);

        if (friend == null) {
            friend = new Friend(user, requester);
        }

        if (!friend.isPending()) {
            player.sendMessage(ChatUtil.sendColor("&cSorry, it seems like that user have never sent request before."));
            return false;
        }

        friend.acceptRequest();
        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
        user.sendMessage("&aYou have accept &f" + requester.getRank().getPrefix() + " " + requester.getNickname() + "&a friend request.");
        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
