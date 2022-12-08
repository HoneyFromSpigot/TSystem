package io.github.thewebcode.tsystem.server.pluginmessaging;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.reflections.MethodMap;
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
        if(!event.getTag().equalsIgnoreCase("tsystem:main")) {
            return;
        }

        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(event.getData()));
        try {
            String serviceID = stream.readUTF();

            System.out.println("Searching for service: " + serviceID);
            TAPI api = TAPI.get();
            MethodMap methodMap = api.getMethodMap();

            methodMap.run(serviceID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
