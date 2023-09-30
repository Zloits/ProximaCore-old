package net.terurion.core.command.troll;

import net.terurion.api.objects.User;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;

public class ILikeDuckCommand extends SingleCommand {

    public ILikeDuckCommand() {
        super("ilikeduck", "duck", "quack");
    }

    @Override
    public boolean run(User user, String[] args) {
        user.sendMessage("Quaack...");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage("Quaaaack");
        return true;
    }
}
