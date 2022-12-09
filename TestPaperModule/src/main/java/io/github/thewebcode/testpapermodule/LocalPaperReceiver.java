package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.IGetableObject;

import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class LocalPaperReceiver {
    public void receive() {
        try (SocketChannel sChannel = SocketChannel.open()) {
            while (true) {
                sChannel.configureBlocking(true);

                if (sChannel.connect(new InetSocketAddress("localhost", 2223))) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(sChannel.socket().getInputStream());
                    Object object = objectInputStream.readObject();

                    if(object instanceof IGetableObject) {
                        IGetableObject lastObject = (IGetableObject) object;
                        AbstractModule abstractModule = (AbstractModule) lastObject.getObject();
                        System.out.println("Model ID:" + abstractModule.getModuleID());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
