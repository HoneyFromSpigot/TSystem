package io.github.thewebcode.tsystem.api;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.TPaperSystem;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.ServerRequest;
import io.github.thewebcode.tsystem.server.ServerResponse;

import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class TAPI {
    private static boolean spigot;
    private HashMap<Class<?>, Object> sourceObjectMap = new HashMap<>();

    private ArrayList<AbstractModule> modules;

    public TAPI(boolean spigot) {
        TAPI.spigot = spigot;
        this.modules = new ArrayList<>();
    }

    public void registerModule(AbstractModule module) {
        modules.add(module);
        module.onEnable();
    }

    public void sendRequest(ServerRequest request, int port) {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(true);
            channel.socket().bind(new InetSocketAddress(port));

            ObjectOutputStream oos = new ObjectOutputStream(channel.accept().socket().getOutputStream());
            oos.writeObject(request);
            oos.close();
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerSourceObject(Object obj){
        sourceObjectMap.put(obj.getClass(), obj);
    }

    public Object getSourceObject(Class<?> clazz){
        return sourceObjectMap.get(clazz);
    }

    public AbstractModule getModule(String name) {
        System.out.println("Run get Module");
        for (AbstractModule module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                System.out.println("Returning Module: "+ module.getModuleID());
                return module;
            }
        }

        return null;
    }



    public AbstractModule getModuleByID(String id) {
        for (AbstractModule module : modules) {
            if (module.getModuleID().equalsIgnoreCase(id)) {
                return module;
            }
        }

        return null;
    }

    public static void register(AbstractModule module) {
        get().registerModule(module);
    }

    public static TAPI get() {
        if(spigot){
            return TPaperSystem.getInstance().getApi();
        }
        return TBungeeSystem.getInstance().getApi();
    }

    public static Logger getLogger() {
        return Logger.getLogger("TSystem");
    }
}
