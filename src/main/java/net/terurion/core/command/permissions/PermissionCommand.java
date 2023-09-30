package net.terurion.core.command.permissions;

import net.terurion.api.objects.User;
import net.terurion.core.api.ParentCommand;
import org.bukkit.command.ConsoleCommandSender;

public class PermissionCommand extends ParentCommand {

    public PermissionCommand() {
        super("permission", "permissions", "perms");

        addSubCommand(new PermissionAddCommand());
        addSubCommand(new PermissionRemoveCommand());
        addSubCommand(new PermissionListCommand());
    }

    @Override
    public boolean run(User user, String[] args) {
        if (user.getRank().getPriority() < 10) {
            user.sendMessage("&cSorry, but this command is only executable by &lADMIN");
            return false;
        }

        if (args.length == 0) {
            user.sendMessage("&aadd/remove/list");
        }

        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
