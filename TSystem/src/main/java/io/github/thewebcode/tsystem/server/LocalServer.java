package io.github.thewebcode.tsystem.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class LocalServer extends Thread{
    private HashMap<String, Object> objects;
    private HashMap<String, Method> methods;
    private ServerResponse response = null;
    private ServerSocket serverSocket;

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

    public void stopServer() {
        try {
            serverSocket.close();
            this.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            this.serverSocket = new ServerSocket(port);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    receive();
                }
            }, 0, 1);

            super.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receive() {
        try{

            while(true){
                Socket client = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                Object object = ois.readObject();

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

            if(object instanceof ServerResponse){
                this.response = (ServerResponse) object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(ServerResponse response, int port) {
        try {
            Socket socket = new Socket("localhost", port);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(response);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerResponse getResponse() {
        return response;
    }
}
