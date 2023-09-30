package net.terurion.core.command.troll;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KaboomCommand extends SingleCommand {

    public KaboomCommand() {
        super("kaboom");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isStaff()) {
            user.sendMessage("&cSorry, but this command is only executable by &lSTAFF");
            return false;
        }

        Player player = user.playerReturn();
        if (args.length == 0) {
            player.setVelocity(new Vector(0, 64, 0));
            player.getWorld().strikeLightning(player.getLocation());
            player.setFallDistance(-65.0F);
            user.sendMessage("&aKaboom!");
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                user.sendMessage("&cSorry, but this player is offline.");
                return false;
            }

            target.setVelocity(new Vector(0, 64, 0));
            target.getWorld().strikeLightning(player.getLocation());
            target.setFallDistance(-65.0F);
            target.sendMessage(ChatUtil.sendColor("&aKaboom!"));
            user.sendMessage("&aKaboom!");
        }

        if (args[0].equalsIgnoreCase("all")) {
            for (Player players : player.getWorld().getPlayers()) {
                players.setVelocity(new Vector(0, 64, 0));
                players.getWorld().strikeLightning(player.getLocation());
                players.setFallDistance(-65.0F);
                players.sendMessage(ChatUtil.sendColor("&aKaboom!"));
                user.sendMessage("&aKaboom!");
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
