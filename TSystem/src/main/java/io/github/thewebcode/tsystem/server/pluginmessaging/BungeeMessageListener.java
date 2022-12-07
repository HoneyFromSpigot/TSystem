package io.github.thewebcode.tsystem.server.pluginmessaging;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class BungeeMessageListener implements Listener {
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {

        System.out.println("Received");
        if(!event.getTag().equalsIgnoreCase("TSystem")) {
            return;
        }

        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(event.getData()));
        try {
            String channel = stream.readUTF();

            System.out.println("Channel is: " + channel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
