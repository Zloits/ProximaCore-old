package net.terurion.core.api;

import net.terurion.core.ProximaCore;
import net.terurion.core.managers.ChannelUserManager;

public class ProximaAPI {
    private static ChannelUserManager channelUserManager;

    public ProximaAPI() {
        channelUserManager = new ChannelUserManager();
    }

    public static ChannelUserManager getChannelUserManager() {
        return channelUserManager;
    }
}
