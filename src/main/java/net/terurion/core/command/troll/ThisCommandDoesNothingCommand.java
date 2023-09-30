package net.terurion.core.command.troll;

import net.terurion.api.objects.User;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;

public class ThisCommandDoesNothingCommand extends SingleCommand {

    public ThisCommandDoesNothingCommand() {
        super("thiscommanddoesnothing");
    }

    @Override
    public boolean run(User user, String[] args) {
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return true;
    }
}
