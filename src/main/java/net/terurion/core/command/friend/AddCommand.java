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

public class AddCommand extends SubCommand {

    public AddCommand() {
        super("add");
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

        if (user.getFriendManager().getFriends().size() == 20) {
            user.sendMessage("&cYou have the maximum of the friends size.");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        User userFriend = SpaceAPI.getSpigotServer().getUser(target.getUniqueId());

        if (userFriend == null) {
            player.sendMessage(ChatUtil.sendColor("&cSorry, but we can't find any user with that name. Or this user has never joined this server before."));
            return false;
        }

        Friend friend = userFriend.getFriend(user);

        if (friend == null) {
            friend = new Friend(userFriend, user);
        }

        if (userFriend.getUUID() == user.getUUID()) {
            user.sendMessage("&cYou cannot sent friend reqeust with yourself.");
            return false;
        }

        if (friend.isFriend()) {
            user.sendMessage("&cYou are already friend with " + userFriend.getNickname());
            return false;
        }

        if (friend.isPending()) {
            user.sendMessage("&cYou are alreadt sent that friend request to " + userFriend.getNickname());
            return false;
        }

        friend.requestFriend();
        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
        player.sendMessage(ChatUtil.sendColor("&aYou have sent friend request with &f" + userFriend.getRank().getPrefix() + " " + userFriend.getNickname() + "&a. They have &c5 &aminutes to accept this request or else your request will be delete."));
        player.sendMessage(ChatUtil.sendColor("&b▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂"));
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage(ChatUtil.sendColor("&cYou can't be add friend in console!"));
        return false;
    }
}
