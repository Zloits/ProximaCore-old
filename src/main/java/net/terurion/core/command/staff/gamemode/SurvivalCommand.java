package net.terurion.core.command.staff.gamemode;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SurvivalCommand extends SingleCommand {

    public SurvivalCommand() {
        super("gms", "survival");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isDev()) {
            user.sendMessage("&cSorry, but this command only can be executable by &6&lDEVELOPER");
            return false;
        }

        Player player = user.playerReturn();
        if (args.length == 0) {
            player.setGameMode(GameMode.SURVIVAL);
            user.sendMessage("Set own gamemode to SURVIVAL");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("all")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.setGameMode(GameMode.SURVIVAL);
                    user.sendMessage("Set all gamemodes to SURVIVAL");
                    players.sendMessage("Set own gamemode to SURVIVAL by " + user.getNickname());
                    return true;
                }
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            User targetUser = SpaceAPI.getSpigotServer().getUser(target.getUniqueId());

            if (target == null) {
                user.sendMessage("&cCan't find any user with that name.");
                return false;
            }

            target.setGameMode(GameMode.SURVIVAL);
            target.sendMessage("Set own gamemode to SURVIVAL by " + user.getNickname());
            user.sendMessage("Set " + targetUser.getNickname() + " gamemodes to SURVIVAL");
        }

        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        if (args.length == 0 || args.length > 1) {
            sender.sendMessage(ChatUtil.sendColor("&cWrong syntax, to use this command you have to insert the player name"));
            return false;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("all")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage("Set all gamemodes to SURVIVAL");
                    players.sendMessage("Set own gamemode to SURVIVAL by Console");
                    return true;
                }
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            User user = SpaceAPI.getSpigotServer().getUser(target.getUniqueId());

            if (target == null) {
                user.sendMessage("&cCan't find any user with that name.");
                return false;
            }

            target.setGameMode(GameMode.SURVIVAL);
            target.sendMessage("Set own gamemode to SURVIVAL by Console");
            sender.sendMessage("Set " + user.getNickname() + " gamemodes to SURVIVAL");
        }

        return true;
    }
}
