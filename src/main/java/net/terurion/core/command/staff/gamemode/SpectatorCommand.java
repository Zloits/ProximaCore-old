package net.terurion.core.command.staff.gamemode;

import net.terurion.api.objects.User;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SpectatorCommand extends SingleCommand {

    public SpectatorCommand() {
        super("gmsp", "spectator");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();
        player.chat("/gamemode spectator");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
