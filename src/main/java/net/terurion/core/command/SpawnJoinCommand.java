package net.terurion.core.command;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SpawnJoinCommand extends SingleCommand {

    public SpawnJoinCommand() {
        super("spawnjoin");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();
        if (args.length > 1) {
            user.sendMessage("&cWrong syntax, to use this command you just have to do /spawnjoin");
            return false;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("all")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.teleport(player.getWorld().getSpawnLocation());
                    players.sendMessage(ChatUtil.sendColor("&aWoa.."));
                    return true;
                }
            }

            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                user.sendMessage("&cCouldn't find any player with that name.");
                return false;
            }

            target.teleport(player.getWorld().getSpawnLocation());
            target.sendMessage("&aWoa..");
            return true;
        }

        player.teleport(player.getWorld().getSpawnLocation());
        user.sendMessage("&aWoa..");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        if (args.length == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.teleport(player.getWorld().getSpawnLocation());
                player.sendMessage(ChatUtil.sendColor("&aWoa.."));
                return true;
            }
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                sender.sendMessage(ChatUtil.sendColor("&cCouldn't find any player with that name."));
                return false;
            }

            target.teleport(target.getWorld().getSpawnLocation());
            target.sendMessage("&aWoa..");
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(ChatUtil.sendColor("&cWrong syntax, to use this command you just have to do /spawnjoin | /spawnjoin <name>"));
            return false;
        }

        return false;
    }
}
