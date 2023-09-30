package net.terurion.core.command.troll;

import net.terurion.api.objects.User;
import net.terurion.api.utils.ChatUtil;
import net.terurion.core.api.SingleCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class IDontKnowWhatIsThisCommand extends SingleCommand {

    public IDontKnowWhatIsThisCommand() {
        super("idontknowwhatisthis", "boom", "thereaboomaboveyourhead");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();

        if (!user.isStaff()) {
            user.sendMessage("I dont know what do you mean. Maybe you want some apples?");
            return false;
        }

        if (args.length == 0) {
            double yLocation = player.getLocation().getY() + 2.0F;
            Location loc = new Location(player.getWorld(), player.getLocation().getX(), yLocation, player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
            player.getWorld().createExplosion(loc, 5.0F, true, false);
            player.setVelocity(new Vector(1, 0, 1));
            user.sendMessage("&c&lBOOM!");
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                user.sendMessage("&cThat player is offline");
                return false;
            }

            double yLocation = target.getLocation().getY() + 2.0F;
            Location loc = new Location(target.getWorld(), target.getLocation().getX(), yLocation, target.getLocation().getZ(), target.getLocation().getYaw(), target.getLocation().getPitch());
            target.getWorld().createExplosion(loc, 5.0F, true, false);
            target.setVelocity(new Vector(1, 0, 1));
            user.sendMessage("&aSend boom to &f" + target.getName());
            target.sendMessage(ChatUtil.sendColor("&c&lBOOM!"));
            return true;
        }

        user.sendMessage("What are you tryna do?");
        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }
}
