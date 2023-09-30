package net.terurion.core.command;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UnNickCommand extends SingleCommand {

    public UnNickCommand() {
        super("unnick", "resetnick");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (user.getRank().getPriority() < 3) {
            user.sendMessage("&cSorry, but this command only can be executable by &9&lDIAMOND &f&cor HIGHER.");
            return false;
        }

        if (args.length > 1) {
            user.sendMessage("&cWrong syntax, to use this command you only have to type /unnick.");
            return false;
        }

        if (args.length == 1) {
            if (!user.isDev()) {
                user.sendMessage("&cSorry, but to include user you have to be &6&lDEVELOPER");
                return false;
            }

            Player player = Bukkit.getPlayerExact(args[0]);
            User target = SpaceAPI.getSpigotServer().getUser(player.getUniqueId());

            if (target == null) {
                user.sendMessage("&cCouldn't find user data.");
                return false;
            }

            target.setNickname(target.getMinecraftName());
            target.sendMessage("&aYour nickname has been reset");
            user.sendMessage("&aNickname for " + target.getRank().getColor() + target.getMinecraftName() + " has been reset.");
            return true;
        }

        user.setNickname(user.getMinecraftName());
        user.sendMessage("&aYour nickname has been reset.");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        if (args.length == 0 || args.length > 1) {
            sender.sendMessage(ChatUtil.sendColor("&cSorry, but to use this command you have to include the name."));
            return false;
        }

        Player player = Bukkit.getPlayerExact(args[0]);
        User user = SpaceAPI.getSpigotServer().getUser(player.getUniqueId());

        if (user == null) {
            user.sendMessage("&cCouldn't find user data.");
            return false;
        }

        user.setNickname(user.getMinecraftName());
        user.sendMessage("&aYour nickname has been reset.");
        sender.sendMessage(ChatUtil.sendColor("&aNickname for " + user.getRank().getColor() + user.getMinecraftName() + " has been reset."));
        return true;
    }
}
