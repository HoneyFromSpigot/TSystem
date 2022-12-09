package io.github.thewebcode.tsystem.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class LocalServer extends Thread{
    private ServerSocketChannel serverSocketChannel;
    private HashMap<String, Object> objects;
    private HashMap<String, Method> methods;

    private int port;

    public LocalServer(int port) {
        if(port == 0){
            throw new IllegalStateException("Port is not setup in config.yml!");
        }

        this.methods = new HashMap<>();
        this.objects = new HashMap<>();
        this.port = port;
    }

    public void register(String id, Object object) {
        if(methods.containsKey(id)){
            objects.put(id, object);
        } else{
            throw new IllegalStateException("No method found for id: " + id);
        }
    }

    public void register(String path){
        try {
            Class<?> clazz = Class.forName(path);
            ArrayList<Method> annotated = new ArrayList<>();
            Method[] clazzMethods = clazz.getMethods();

            for (Method method : clazzMethods) {
                if (method.isAnnotationPresent(ServiceMethod.class)) {
                    annotated.add(method);
                }
            }

            if(!annotated.isEmpty()){
                for (Method method : annotated) {
                    methods.put(method.getAnnotation(ServiceMethod.class).serviceID(), method);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                receive();
            }
        }, 50, 500);

        super.start();
    }

    private void receive() {
        try{
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(true);

                if(socketChannel.connect(new InetSocketAddress("localhost", port))) {
                    ObjectInputStream stream = new ObjectInputStream(socketChannel.socket().getInputStream());
                    Object object = stream.readObject();
                    handleReceivedObject(object);
                }
        } catch (Exception ignore) {
        }
    }

    private void handleReceivedObject(Object object) {
        try {
            if (object instanceof ServerRequest) {
                ServerRequest request = (ServerRequest) object;
                String serviceID = request.getServiceID();

                if (!methods.containsKey(serviceID)) return;

                Method methodToInvoke = methods.get(serviceID);

                Object source = objects.containsKey(serviceID) ? objects.get(serviceID) : methodToInvoke.getDeclaringClass().newInstance();

                Object[] args = request.getArgs();

                Object returned = args == null || args.length == 0 ? methodToInvoke.invoke(source) : methodToInvoke.invoke(source, args);

                if(returned != null){
                    ServerResponse response = new ServerResponse(returned, serviceID);
                    sendResponse(response, request.getReturningPort());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(ServerResponse response, int port) {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(true);
            channel.socket().bind(new InetSocketAddress(port));

            ObjectOutputStream oos = new ObjectOutputStream(channel.accept().socket().getOutputStream());
            oos.writeObject(response);
            oos.close();
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
