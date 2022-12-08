package io.github.thewebcode.tsystem.server.pluginmessaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.reflections.MethodMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class MessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("tsystem:main")) return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);

        String subChannel = input.readUTF();

        if(subChannel.equals("runService")) {
            String serviceID = input.readUTF();

            MethodMap methodMap = TAPI.get().getMethodMap();

            System.out.println("Searching for service: " + serviceID);

            if(methodMap.containsService(serviceID)) {
                System.out.println("Found service: " + serviceID);
                methodMap.run(serviceID);
            }
        }
    }
}
