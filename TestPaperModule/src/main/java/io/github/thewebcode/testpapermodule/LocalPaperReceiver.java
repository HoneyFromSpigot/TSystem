package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.IGetableObject;

import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class LocalPaperReceiver {
    private IGetableObject lastObject;
    public void receive() {
        try {
            while (true) {
                SocketChannel sChannel = SocketChannel.open();
                sChannel.configureBlocking(true);

                if (sChannel.connect(new InetSocketAddress("localhost", 2223))) {
                    ObjectInputStream ois = new ObjectInputStream(sChannel.socket().getInputStream());
                    Object o = ois.readObject();

                    if(o instanceof IGetableObject) {
                        lastObject = (IGetableObject) o;
                        AbstractModule m = (AbstractModule) lastObject.getObject();
                        System.out.println("Model ID:" + m.getModuleID());
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}