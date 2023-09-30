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
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class SingleCommand extends Command {
    private SimpleCommandMap commandMap;
    private SimplePluginManager pluginManager;

    public SingleCommand(String name, String... aliases) {
        super(name);

        if (aliases != null || aliases.length > 0) {
            this.setAliases(Arrays.stream(aliases).collect(Collectors.toList()));
        }

        try {
            pluginManager = (SimplePluginManager) Bukkit.getServer().getPluginManager();
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);

            commandMap = (SimpleCommandMap) field.get(pluginManager);
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

            run(user, args);
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender consoleSender = (ConsoleCommandSender) sender;
            runConsole(consoleSender, args);
            return true;
        }

        return false;
    }
}
