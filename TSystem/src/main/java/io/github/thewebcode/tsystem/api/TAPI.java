package io.github.thewebcode.tsystem.api;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.TPaperSystem;
import io.github.thewebcode.tsystem.api.utils.ModuleFileManager;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.reflections.MethodMap;
import io.github.thewebcode.tsystem.server.reflections.ServiceClass;
import io.github.thewebcode.tsystem.server.reflections.ServiceMethod;

import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class TAPI {
    private static boolean spigot;
    private MethodMap methodMap = new MethodMap();
    private HashMap<Class<?>, Object> sourceObjectMap = new HashMap<>();

    private ArrayList<AbstractModule> modules;
    private ModuleFileManager fileManager;

    public TAPI(boolean spigot) {
        TAPI.spigot = spigot;
        this.modules = new ArrayList<>();
        this.fileManager = new ModuleFileManager();
    }

    public void registerModule(AbstractModule module) {
        modules.add(module);
        fileManager.getConfigFile(module);
        module.onEnable();
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

    public void register(MethodMap map){
        this.methodMap.add(map);
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

    public ModuleFileManager getFileManager() {
        return fileManager;
    }

    public MethodMap getMethodMap() {
        return methodMap;
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
            map.add(method, method.getAnnotation(ServiceMethod.class).serviceID());
        });

        return map;
    }
}
