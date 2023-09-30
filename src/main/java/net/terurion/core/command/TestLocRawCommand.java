package net.terurion.core.command;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.type.data.ServerType;
import net.terurion.core.ProximaCore;
import net.terurion.core.api.SingleCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TestLocRawCommand extends SingleCommand {
    private ProximaCore plugin = ProximaCore.getInstance();

    public TestLocRawCommand() {
        super("testlocraw");
    }

    @Override
    public boolean run(User user, String[] args) {
        Player player = user.playerReturn();
        player.sendMessage("DatabaseID: {name-" + SpaceAPI.getServerId() + "}, ServerID: {" + SpaceAPI.getServerId() + "}, ServerType: {" + ServerType.getTypeById(SpaceAPI.getServerId()).getDefaultName() + "}, ServerName: {" + plugin.getConfig().getString("Server.DefaultName") + "}.");
        return false;
    }

    @Override
    public boolean runConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage("DatabaseID: {name-" + SpaceAPI.getServerId() + "}, ServerID: {" + SpaceAPI.getServerId() + "}, ServerType: {" + ServerType.getTypeById(SpaceAPI.getServerId()).getDefaultName() + "}, ServerName: {" + plugin.getConfig().getString("Server.DefaultName") + "}.");
        return false;
    }
}
