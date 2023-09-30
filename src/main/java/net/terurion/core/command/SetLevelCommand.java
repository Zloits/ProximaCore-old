package net.terurion.core.command;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetLevelCommand extends SingleCommand {

    public SetLevelCommand() {
        super("setlevel");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isDev()) {
            user.sendMessage("&cSorry, but to use this command you have to be &6&lDEVELOPER");
            return false;
        }

        if (args.length < 2 || args.length > 2) {
            user.sendMessage("&cWrong syntax, to use this command you have to insert User's and Level Amount");
            return false;
        }

        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
        User target = SpaceAPI.getSpigotServer().getUser(targetPlayer.getUniqueId());

        int levelSetter = Integer.parseInt(args[1]);

        target.getLevelManager().setLevel(levelSetter);
        target.sendMessage("&aYour level has been changed to &f" + levelSetter);
        user.sendMessage(target.getRank().getColor() + target.getNickname() + " &alevel has been changed to &f" + levelSetter);

        targetPlayer.setLevel(levelSetter);

        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        if (args.length < 2 || args.length > 2) {
            sender.sendMessage(ChatUtil.sendColor("&cWrong syntax, to uset his command you have to include the name and the amount of the level."));
            return false;
        }

        Player player = Bukkit.getPlayerExact(args[0]);
        User user = SpaceAPI.getSpigotServer().getUser(player.getUniqueId());

        int levelSetter = Integer.parseInt(args[1]);

        user.getLevelManager().setLevel(levelSetter);
        user.sendMessage("&aYour level has been changed to &f" + levelSetter);
        sender.sendMessage(ChatUtil.sendColor(user.getRank().getColor() + user.getNickname() + " &alevel has been changed to &f" + levelSetter));
        return true;
    }
}