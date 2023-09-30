package net.terurion.core.command.guild;

import net.terurion.api.objects.User;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;

public class GuildListCommand extends SingleCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public GuildListCommand() {
        super("guildlist", "gl");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!plugin.getConfig().getBoolean("Modules.GuildCommands")) {
            user.sendMessage("&cSorry, but guid isn't available for this server.");
            return false;
        }

        if (user.getGuild() == null) {
            user.sendMessage("&cYou are not in a guild!");
            return false;
        }

        user.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
        user.sendMessage("&a              Guild list");
        user.sendMessage("&f &f");

        for (User player : user.getGuild().getGuildMember().getMembers().keySet()) {
            user.sendMessage("&7 - " + player.getRank().getColor() + player.getNickname());
        }

        user.sendMessage("&f &f &f");
        user.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
