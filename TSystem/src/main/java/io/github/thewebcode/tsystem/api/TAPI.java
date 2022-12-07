package io.github.thewebcode.tsystem.api;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.api.utils.ModuleFileManager;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.IAction;
import io.github.thewebcode.tsystem.server.reflections.MethodMap;
import io.github.thewebcode.tsystem.server.reflections.ServiceClass;
import io.github.thewebcode.tsystem.server.reflections.ServiceMethod;
import org.reflections.Reflections;

import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class TAPI {
    private MethodMap methodMap = new MethodMap();

    private ArrayList<AbstractModule> modules;
    private ModuleFileManager fileManager;

    public TAPI() {
        this.modules = new ArrayList<>();
        this.fileManager = new ModuleFileManager();

    }

    public void registerModule(AbstractModule module) {
        modules.add(module);
        fileManager.getConfigFile(module);
        module.onEnable();
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

    public void sendToServer(Object obj){
        int defaultPort = 2223;
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(true);
            serverSocketChannel.socket().bind(new InetSocketAddress(defaultPort));
            ObjectOutputStream oos = new ObjectOutputStream(serverSocketChannel.socket().accept().getOutputStream());
            oos.writeObject(obj);
            oos.close();
            serverSocketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MethodMap map(AbstractModule module, Class<?> clazz) {
        if (!clazz.isAnnotationPresent(ServiceClass.class)) {
            System.out.println("Class is not annotated with ServiceClass");
            return null;
        }

        Method[] methods = clazz.getMethods();

        Set<Method> methodsAnnotatedWith = new HashSet<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ServiceMethod.class)) {
                methodsAnnotatedWith.add(method);
            }
        }
        MethodMap map = new MethodMap();

        methodsAnnotatedWith.forEach(method -> {
            map.add(method, module.getModuleID() + "-" + method.getAnnotation(ServiceMethod.class).serviceID());
        });

        return map;
    }

    public void register(MethodMap map){
        this.methodMap.add(map);
    }

    public ModuleFileManager getFileManager() {
        return fileManager;
    }

    public static void register(AbstractModule module) {
        get().registerModule(module);
    }

    @Deprecated
    public static TAPI get() {
        return TBungeeSystem.getInstance().getApi();
    }

    public static Logger getLogger() {
        return Logger.getLogger("TSystem");
    }
}
