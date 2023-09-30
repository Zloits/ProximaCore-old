package net.terurion.core.command;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;

public class TestOpenStoreCommand extends SingleCommand {

    public TestOpenStoreCommand() {
        super("testopenstore");
    }

    @Override
    public boolean run(User user, String[] args) {
        user.sendMessage("&cWe're still working on it.");
        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage(ChatUtil.sendColor("&cWe're still working on it."));
        return false;
    }
}
