package net.terurion.core.command.troll;

import net.terurion.api.objects.User;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FreeOPCommand extends SingleCommand {

    public FreeOPCommand() {
        super("op", "freeop");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();
        player.sendMessage("You are now the operator of the server. :)");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage("You are now the operator of the server. :)");
        return true;
    }
}
