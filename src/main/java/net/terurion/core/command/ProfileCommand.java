package net.terurion.core.command;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.objects.items.Icon;
import net.terurion.api.objects.items.ItemBuilder;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ProfileCommand extends SingleCommand {

    public ProfileCommand() {
        super("profile");
    }

    @Override
    public boolean run(User user, String[] args) {
        if (args.length == 0) {
            Player player = user.playerReturn();
            Inventory profileGUI = Bukkit.createInventory(null, 54, ChatUtil.sendColor("&f" + user.getNickname() + "'s &aProfile"));

            int[] glasses = {10, 11, 12, 13, 14, 15, 16};

            for (int glass : glasses) {
                ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("");
                item.setItemMeta(meta);
                profileGUI.setItem(glass, item);
            }
            return true;
        }

        if (args.length == 1) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            User target = SpaceAPI.getSpigotServer().getUser(offlinePlayer.getUniqueId());

            openProfileGUI(user, target);
            return true;
        }

        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }

    private void openProfileGUI(User opener, User target) {
        Player player = opener.playerReturn();
        Inventory profileGUI = Bukkit.createInventory(null, 54, ChatUtil.sendColor("&f" + target.getNickname() + "'s &aProfile"));

        int[] glasses = {10, 11, 12, 13, 14, 15, 16};

        for (int glass : glasses) {
            ItemBuilder glass_item = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, false);
            glass_item.setName("&f &f");
            glass_item.next();
            new Icon(profileGUI, glass_item, glass);
        }
    }
}
