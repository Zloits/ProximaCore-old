package net.terurion.core.command.staff;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand extends SingleCommand {

    public ItemCommand() {
        super("item");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (!user.isAdmin()) {
            user.sendMessage("&cSorry, but this command is only can be executable by &lADMIN");
            return false;
        }

        if (args.length == 0 || args.length > 3) {
            user.sendMessage("&cWrong syntax. To use this command you to do like this:");
            user.sendMessage("&c- &7/item <Item> <Amount>");
            user.sendMessage("&c- &7/item <Item> <Amount> <Player/All>");
            return false;
        }

        Player player = user.playerReturn();
        if (args.length == 1) {
            String itemName = args[0];

            ItemStack item = new ItemStack(Material.valueOf(itemName), 1);

            if (item == null) {
                user.sendMessage("&cCan't find any item.");
                return false;
            }

            player.getInventory().addItem(item);
            user.sendMessage("&aItem &f" + itemName.toUpperCase() + " &ahas been added to your inventory.");
            return true;
        }

        if (args.length == 2) {
           String itemName = args[0];
           int itemAmount = Integer.parseInt(args[1]);

           ItemStack item = new ItemStack(Material.valueOf(itemName), itemAmount);

            if (item == null) {
                user.sendMessage("&cCan't find any item.");
                return false;
            }

           player.getInventory().addItem(item);
           user.sendMessage("&aItem &f" + itemName.toUpperCase() + "&c x&7" + itemAmount + " &ahas been added to your inventory.");
           return true;
        }

        if (args.length == 3) {
            String itemName = args[0];
            int itemAmount = Integer.parseInt(args[1]);

            if (args[2].equalsIgnoreCase("all") || args[2].equalsIgnoreCase("@a") || args[2].equalsIgnoreCase("a")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    ItemStack item = new ItemStack(Material.valueOf(itemName), itemAmount);

                    if (item == null) {
                        user.sendMessage("&cCan't find any item.");
                        return false;
                    }

                    players.getInventory().addItem(item);
                    players.sendMessage(ChatUtil.sendColor("&f" + itemName.toUpperCase() + " &cx&7" + itemAmount + " &ahas been added to your inventory."));
                    return true;
                }
            }

            Player target = Bukkit.getPlayerExact(args[2]);
            ItemStack item = new ItemStack(Material.valueOf(itemName), itemAmount);

            if (target == null) {
                user.sendMessage("&cLook like that player is offline.");
                return false;
            }

            if (item == null) {
                user.sendMessage("&cCan't find any item.");
                return false;
            }

            target.getInventory().addItem(item);
            target.sendMessage(ChatUtil.sendColor("&f" + itemName.toUpperCase() + " &cx&7" + itemAmount + " &ahas been added to your inventory."));
            return true;
        }
          
        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
