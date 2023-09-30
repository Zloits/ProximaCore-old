package net.terurion.core.adapters;

import net.terurion.api.objects.User;

import java.util.UUID;

public interface CUserAdapter {

    public UUID getUUID();

    public String getDisplayName();

    public User getUser();

    public boolean isOnline();

    public void sendMessage(String message);

    public void send(String serverName);
}
