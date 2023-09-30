package net.terurion.core.command.guild;

import net.terurion.api.objects.User;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.ParentCommand;
import net.terurion.core.api.SubCommand;
import org.bukkit.command.ConsoleCommandSender;

public class GuildCommand extends ParentCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public GuildCommand() {
        super("guild", "g", "guilds");

        addSubCommand(new CreateCommand());
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!plugin.getConfig().getBoolean("Modules.GuildCommands")) {
            user.sendMessage("&cSorry, but guid isn't available for this server.");
            return false;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            user.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
            user.sendMessage("&a         Guild Help Command.");
            user.sendMessage("&f &a");

            for (SubCommand subCommand : getSubCommands()) {
                user.sendMessage("&7- &f/guild " + subCommand.getName());
            }

            user.sendMessage("&f &a &f");
            user.sendMessage("&2&m▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂ ");
            return true;
        } return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
