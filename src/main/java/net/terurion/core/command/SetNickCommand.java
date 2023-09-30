package net.terurion.core.command;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class SetNickCommand extends SingleCommand {

    public SetNickCommand() {
        super("setnick");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();

        if (user.getRank().getPriority() < 3) {
            player.sendMessage(ChatUtil.sendColor("&cSorry, but this command is only executable by &9&lDIAMOND &f&cor &lHIGHER"));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(ChatUtil.sendColor("&cWrong syntax, to use this command you have to insert Nickname."));
            return false;
        }

        if (args.length > 1) {
            player.sendMessage(ChatUtil.sendColor("&cWrong syntax, to use this command you have to insert Nickname."));
            return false;
        }

        String nickname = args[0];

        if (!Pattern.compile("[a-zA-Z0-9_]*").matcher(nickname).matches()) {
             user.sendMessage("&cSorry, but you cannot use special characters.");
             return false;
        }

        if (nickname.length() < 4) {
            player.sendMessage(ChatUtil.sendColor("&cSorry, please atleast nickname length is &l4"));
            return false;
        }

        if (nickname.length() > 16) {
            player.sendMessage(ChatUtil.sendColor("&cSorry, you can't use this nick. Because the maximum length is &l16"));
            return false;
        }

        player.sendMessage(ChatUtil.sendColor("&aYou have changed your nickname to &2" + nickname));

        user.setNickname(nickname);
        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage(ChatUtil.sendColor("&cFor now, this command is not available for console. Please wait for update coming up soon, to execute this."));
        return false;
    }
}
