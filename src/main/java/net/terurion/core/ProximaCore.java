package net.terurion.core;

import net.terurion.api.SpaceAPI;
import net.terurion.api.objects.User;
import net.terurion.api.type.Rank;
import net.terurion.api.type.data.ServerType;
import net.terurion.core.command.*;
import net.terurion.core.command.friend.FriendCommand;
import net.terurion.core.command.friend.FriendListCommand;
import net.terurion.core.command.guild.GuildCommand;
import net.terurion.core.command.guild.GuildListCommand;
import net.terurion.core.command.permissions.PermissionCommand;
import net.terurion.core.command.staff.ItemCommand;
import net.terurion.core.command.staff.RegGuildsCommand;
import net.terurion.core.command.staff.SetSpawnCommand;
import net.terurion.core.command.staff.gamemode.SpectatorCommand;
import net.terurion.core.command.staff.gamemode.SurvivalCommand;
import net.terurion.core.command.troll.*;
import net.terurion.core.listeners.OnChat;
import net.terurion.core.listeners.OnCommandSent;
import net.terurion.core.listeners.OnJoin;
import net.terurion.core.managers.PermissionManager;
import net.terurion.core.objects.messaging.PluginMessageChannel;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.UUID;

public final class ProximaCore extends JavaPlugin {
    private static ProximaCore instance;
    private SQL sql;

    private PermissionManager permissionManager;

    @Override
    public void onEnable() {
        instance = this;

        try {
            sql = new SQL(
                    getConfig().getString("SQL.Host"),
                    getConfig().getString("SQL.Database"),
                    getConfig().getString("SQL.User"),
                    getConfig().getString("SQL.Pass"),
                    getConfig().getInt("SQL.Port"),
                    getConfig().getBoolean("SQL.UseSSL")
            );

            sql.connect();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessageChannel());

        permissionManager = new PermissionManager();

        saveDefaultConfig();
        SpaceAPI.setServerId(ServerType.getTypeById(getConfig().getInt("Server.ID")));
        SpaceAPI.getSpigotServer().getGuildManager().reloadGuild();

        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new OnChat(), this);
        getServer().getPluginManager().registerEvents(new OnCommandSent(), this);

        new WhatIsMyRankCommand();
        new SetRankCommand();
        new SetNickCommand();
        new TestLocRawCommand();
        new LocRawCommand();
        new ProfileCommand();
        new RegGuildsCommand();
        new UnNickCommand();
        new ReloadChangesCommand();

        new SetSpawnCommand();
        new SpawnJoinCommand();
        new ItemCommand();

        new FriendListCommand();
        new FriendCommand();

        new GuildCommand();
        new GuildListCommand();

        new TestOpenStoreCommand();
        new PermissionCommand();
        new SetLevelCommand();
        new SurvivalCommand();
        new SpectatorCommand();

        getLogger().info("Checking for trolls permissions.");
        if (getConfig().getBoolean("Modules.TrollCommands")) {
            getLogger().info("Result: YES. Trying to load troll modules.");
            new FreeOPCommand();
            new FreeGmcCommand();
            new ILikeDuckCommand();
            new ThisCommandDoesNothingCommand();
            new KaboomCommand();
            new IDontKnowWhatIsThisCommand();
            getLogger().info("Load for troll modules is done.");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Checking & saving permissions for (" + getPermissionManager().getRankPerms().size() + ") rank's...");
    }

    public static ProximaCore getInstance() {
        return instance;
    }

    public PermissionManager getPermissionManager() {
        return permissionManager;
    }
}
