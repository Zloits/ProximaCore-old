package net.terurion.core.command;

import net.terurion.api.objects.User;
import net.terurion.api.type.Rank;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class WhatIsMyRankCommand extends SingleCommand {

    public WhatIsMyRankCommand() {
        super("whatismycurrentrank", "whatismyrank", "myrank");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();

        if (user.getRank() == null) {
            player.sendMessage(ChatUtil.sendColor("&cWait... seems like you have no ranks in our database. Please contact our developers to fix this bug."));
            return false;
        }

        if (user.getRank() == Rank.DEFAULT) {
            user.sendMessage("&bYour current rank is&7: Default");
            return true;
        }

        player.sendMessage(ChatUtil.sendColor("&bYour current rank is&7: " + user.getRank().getPrefix()));
        return true;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage(ChatUtil.sendColor("&cThis command is unavailable for console."));
        return false;
    }
}
