package net.terurion.core.command;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.type.Rank;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.ProximaAPI;
import net.terurion.core.api.SingleCommand;
import net.terurion.core.objects.ChannelUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SetRankCommand extends SingleCommand {

    public SetRankCommand() {
        super("setrank");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isAdmin()) {
            user.sendMessage("&cSorry, this coammnd only can be executable by &lADMIN");
            return false;
        }

        if (args.length < 2 || args.length > 2) {
            user.sendMessage("&cWrong syntax, to use this command you have to include the name, and the rank name");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        ChannelUser targetCUser = ProximaAPI.getChannelUserManager().getChannelUser(target.getUniqueId());

        User targetUser = SpaceAPI.getSpigotServer().getUser(target.getUniqueId());

        if (targetUser == null) {
            user.sendMessage("&cCouldn't find any player.");
            return false;
        }

        String rankName = args[1];
        Rank rank = Rank.getRankByName(rankName);

        int newPriority = rank.getPriority();

        if (rank == null) {
            user.sendMessage("&cThere no rank with that such name.");
            return false;
        }

        user.sendMessage("&aYou have set " + targetUser.getRank().getColor() + targetUser.getNickname() + " &arank to " + rank.getPrefix());
        targetUser.setRank(rank);

        if (targetUser.getRank().getPriority() > newPriority) {
            if (target == null) {
                if (targetCUser.isOnline()) {
                    targetCUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
                    targetCUser.sendMessage("&a Your rank has been promoted to " + rank.getPrefix() + " &aby " + user.getRank().getColor() + user.getNickname());
                    targetCUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
                }
            }

            targetUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
            targetUser.sendMessage("&a Your rank has been promoted to " + rank.getPrefix() + " &aby " + user.getRank().getColor() + user.getNickname());
            targetUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
        }

        if (targetUser.getRank().getPriority() < newPriority) {
            if (target == null) {
                if (targetCUser.isOnline()) {
                    targetCUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
                    targetCUser.sendMessage(" &2Your rank has been demoted to "+ rank.getColor() + rank.getDefaultName() + " &aby " + user.getRank().getColor() + user.getNickname());
                    targetCUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
                }
            }

            targetUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
            targetUser.sendMessage("&a Your rank has been promoted to " + rank.getColor() + rank.getDefaultName() + " &aby " + user.getRank().getColor() + user.getNickname());
            targetUser.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
        }

        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatUtil.sendColor("&cSorry, but to use this command you to insert Player and Rank Type."));
            return false;
        }

        if (args.length > 2) {
            sender.sendMessage(ChatUtil.sendColor("&cSorry, but to use this command you to insert Player and Rank Type."));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        User user = SpaceAPI.getSpigotServer().getUser(target.getUniqueId());

        if (user == null) {
            sender.sendMessage(ChatUtil.sendColor("&cSorry, but we can't find any user with that name. Or this user has never joined this server before."));
            return false;
        }

        String rankName = args[1];
        Rank rank = Rank.getRankByName(rankName);

        if (rank == null) {
            sender.sendMessage(ChatUtil.sendColor("&cWe can't find any ranks with that name. Please re-check the rank name that you insert."));
            return false;
        }

        if (user.getRank() == rank) {
            sender.sendMessage(ChatUtil.sendColor("&cThis user's already in that rank."));
            return false;
        }

        int currentPriority = Rank.getRankByName(rankName).getPriority();

        if (rank.getPriority() > currentPriority) {
            if (currentPriority == 0) {
                sender.sendMessage(ChatUtil.sendColor("&aYou have demoted &f" + user.getNickname() + "'s rank&a to &7Default"));
            }

            sender.sendMessage(ChatUtil.sendColor("&aYou have promoted &f" + user.getNickname() + "'s rank&a to " + Rank.getRankByName(rankName).getPrefix()));
            user.setRank(Rank.getRankByName(rankName.toUpperCase()));
        }

        if (rank.getPriority() < currentPriority) {
            if (currentPriority == 0) {
                sender.sendMessage(ChatUtil.sendColor("&aYou have demoted &f" + user.getNickname() + "'s rank&a to &7Default"));
            }

            sender.sendMessage(ChatUtil.sendColor("&aYou have demoted &f" + user.getNickname() + "'s rank&a to " + Rank.getRankByName(rankName).getPrefix()));
            user.setRank(Rank.getRankByName(rankName.toUpperCase()));
        }

        user.setRank(Rank.getRankByName(rankName.toUpperCase()));
        Player targetPlayer = target.getPlayer();
        targetPlayer.recalculatePermissions();
        targetPlayer.updateCommands();
        return true;
    }
}
