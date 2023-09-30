package net.terurion.core.command.permissions;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.type.Rank;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.SubCommand;
import net.terurion.core.objects.RankPermission;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;

public class PermissionRemoveCommand extends SubCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public PermissionRemoveCommand() {
        super("remove");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (args.length < 3 || args.length > 3) {
            user.sendMessage("&cWrong syntax. To use this command you have to insert Rank's and Perm");
            return false;
        }

        Rank rank = Rank.getRankByName(args[1]);
        String perm = args[2];

        RankPermission rankPermission = plugin.getPermissionManager().getPermission(rank);

        if (!rankPermission.getPerms().contains(perm)) {
            user.sendMessage("&cCan't find any permission.");
            return false;
        }

        rankPermission.removePermission(perm);
        plugin.getPermissionManager().reloadPermission(rank);
        user.sendMessage("&aRank " + rank.getPrefix() + " &apermission has remove &f" + perm + " &apermission.");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
