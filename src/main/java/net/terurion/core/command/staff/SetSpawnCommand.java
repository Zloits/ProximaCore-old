package net.terurion.core.command.staff;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends SingleCommand {

    public SetSpawnCommand() {
        super("setspawn");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isStaff()) {
            user.sendMessage("&cNot today.");
            return false;
        }

        Player player = user.playerReturn();
        player.getWorld().setSpawnLocation(player.getLocation());
        user.sendMessage("&aSpawn set for world: " + player.getWorld().getName());
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage(ChatUtil.sendColor("&cSorry cannot set spawn in console."));
        return false;
    }
}
