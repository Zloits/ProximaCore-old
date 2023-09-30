package net.terurion.core.api;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ParentCommand extends Command {
    private List<SubCommand> subCommands = new ArrayList<>();

    protected ParentCommand(String name, String... aliases) {
        super(name);

        if (aliases != null || aliases.length > 0) {
            this.setAliases(Arrays.stream(aliases).collect(Collectors.toList()));
        }

        try {
            SimplePluginManager pluginManager = (SimplePluginManager) Bukkit.getServer().getPluginManager();
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);

            SimpleCommandMap commandMap = (SimpleCommandMap) field.get(pluginManager);
            commandMap.register("ProximaCore", this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract boolean run(User user, String[] args);

    public abstract boolean runConsole(ConsoleCommandSender sender, String[] args);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            User user = SpaceAPI.getSpigotServer().getUser(player.getUniqueId());

            if (user == null) {
                sender.sendMessage(ChatUtil.sendColor("&cSorry, but we couldn't find your profile."));
            }

            if (args.length > 0) {
                for (SubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName()) || args[0].equalsIgnoreCase(subCommand.getAliases().toString())) {
                        subCommand.run(user, args);
                        return true;
                    }
                }
            }

            run(user, args);
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender consoleSender = (ConsoleCommandSender) sender;

            if (args.length > 0) {
                for (SubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.runConsole(consoleSender, args);
                    }
                }
            }

            runConsole(consoleSender, args);
            return true;
        }

        return false;
    }

    // SubCommand

    protected void addSubCommand(SubCommand subCommand) {
        if (!subCommands.contains(subCommand)) {
            subCommands.add(subCommand);
        }
    }

    protected List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
