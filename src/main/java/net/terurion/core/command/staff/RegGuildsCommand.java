package net.terurion.core.command.staff;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.objects.guild.Guild;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;

public class RegGuildsCommand extends SingleCommand {
    
    public RegGuildsCommand() {
        super("regguilds");
    }
    
    @Override
    public boolean run(User user, String[] args) {
        if (!user.isDev()) {
            user.sendMessage("&cSorry, but to create guild, you must atleast rank &6&lDEVELOPER &f&cor HIGHER.");
            return false;
        }

        user.sendMessage("Guild List: " + SpaceAPI.getSpigotServer().getGuilds().size());
        for (Guild guild : SpaceAPI.getSpigotServer().getGuilds().values()) {
            user.sendMessage(guild.toString());
        }

        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage("Guild List: " + SpaceAPI.getSpigotServer().getGuilds().size());
        for (Guild guild : SpaceAPI.getSpigotServer().getGuilds().values()) {
            sender.sendMessage(guild.toString());
        }

        return true;
    }
}
