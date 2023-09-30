package net.terurion.core.command.guild;

import net.terurion.api.SpaceAPI;
import net.terurion.api.manager.GuildManager;
import net.terurion.api.objects.User;
import net.terurion.api.objects.guild.Guild;
import net.terurion.api.type.GuildRole;
import net.terurion.core.api.SubCommand;
import org.bukkit.command.ConsoleCommandSender;

import java.util.UUID;
import java.util.regex.Pattern;

public class CreateCommand extends SubCommand {

    public CreateCommand() {
        super("create");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (args.length < 2 || args.length > 2) {
            user.sendMessage("&cWrong syntax, to use this command you have to insert Guild Name.");
            return false;
        }

        if (user.getRank().getPriority() < 2) {
            user.sendMessage("&cSorry, but to create guild, you must atleast rank &6&lGOLD &f&cor HIGHER.");
            return false;
        }

        if (user.getGuild() != null) {
            user.sendMessage("&cYou're already joined guild.");
            return false;
        }

        String guildName = args[1];

        if (guildName.length() < 5 || args.length > 16) {
            user.sendMessage("&cSorry, But the minimum name is 5 and the maximum name is 16. Please re-check your name it's must be at the minimum or maximum.");
            return false;
        }

        if (!Pattern.compile("[a-zA-Z0-9]*").matcher(guildName).matches()) {
            user.sendMessage("&cSorry, but you cannot use special characters.");
            return false;
        }

        GuildManager guildManager = SpaceAPI.getSpigotServer().getGuildManager();

        if (guildManager.getGuild(guildName) != null) {
            user.sendMessage("&cSorry, but guild with the name &l" + guildName + " &f&cis already exists.");
            return false;
        }

        UUID uuid = UUID.randomUUID();
        Guild guild = new Guild(uuid, guildName, "Some new guild", 10, user);
        guild.getGuildMember().addmember(user, GuildRole.GUILDMASTER);
        guildManager.createGuild(guild);

        user.sendMessage("&f &f");
        user.sendMessage("&2▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂ ");
        user.sendMessage("&a Done! you're is now ready to use! &2/Guild info &afor the guild information. And also don't forgot to invite your friends!");
        user.sendMessage("&2▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂");
        user.sendMessage("&7You're guild ID: &8" + guild.getGuildId());

        if (guild == null) {
            user.sendMessage("&CError, it seems like we have problems with our systems. Please contact the developers and send code &lGIN");
            return false;
        }

        user.setGuild(guild, GuildRole.GUILDMASTER);
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
