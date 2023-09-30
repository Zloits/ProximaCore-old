package net.terurion.core.command.permissions;

import net.terurion.api.objects.User;
import net.terurion.api.type.Rank;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.SubCommand;
import net.terurion.core.objects.RankPermission;
import org.bukkit.command.ConsoleCommandSender;

public class PermissionListCommand extends SubCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public PermissionListCommand() {
        super("list");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (args.length < 2 || args.length > 2) {
            user.sendMessage("&cWrong syntax. Please don't add the new length.");
            return false;
        }

        Rank rank = Rank.getRankByName(args[1]);
        RankPermission rankPermission = plugin.getPermissionManager().getPermission(rank);

        if (rankPermission.getPerms() == null || rankPermission.getPerms().size() == 0) {
            user.sendMessage("&cThere's no permission at this rank.");
            return false;
        }

        user.sendMessage("| (" + rank.getPrefix() + "&f) &apermissions: (&f" + rankPermission.getPerms().size() + "&2)");
        user.sendMessage("&f &f");
        for (String perm : rankPermission.getPerms())
            user.sendMessage("&7- &f" + perm);
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
