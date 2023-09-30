package net.terurion.core.command.troll;

import net.terurion.api.objects.User;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;

public class FreeGmcCommand extends SingleCommand {

    public FreeGmcCommand() {
        super("freegmc", "freegmctrustme");
    }

    @Override
    public boolean run(User user, String[] args) {
        user.sendMessage("You are now gamemo- Wait i think there's an error. Maybe you got this later....");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
