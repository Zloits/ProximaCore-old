package net.terurion.core.api;

import net.terurion.api.objects.User;
import org.bukkit.command.ConsoleCommandSender;

public abstract class SubCommand {
    private String name;
    private String[] aliases;

    public SubCommand(String name, String... aliases) {
        this.name = name;

        if (aliases != null || aliases.length > 0) {
            this.aliases = aliases;
        }
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public abstract boolean run(User user, String[] args);

    public abstract boolean runConsole(ConsoleCommandSender sender, String[] args);
}
