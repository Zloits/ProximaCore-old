package net.terurion.core.command.friend;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.ParentCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FriendCommand extends ParentCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public FriendCommand() {
        super("friend", "f");

        addSubCommand(new AddCommand());
        addSubCommand(new AcceptCommand());
        addSubCommand(new DeclineCommand());
        addSubCommand(new RequestCommand());
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!plugin.getConfig().getBoolean("Modules.FriendCommands")) {
            user.sendMessage("&cSorry, but friend isn't available for this server.");
            return false;
        }

        Player player = user.playerReturn();

        if (!user.isStaff()) {
            user.sendMessage("&cSorry, but this command is still on development, we are very sorry to this mistakes that we make.");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ChatUtil.sendColor("&aHelp Command."));
            return true;
        }

        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
