package io.github.thewebcode.tsystem.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class LocalServerManager {
    private ServerSocketChannel serverSocketChannel;
    private int port;
    public void startServer(int port){
        this.port = port;
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(true);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.accept();

            System.out.println("Server started on port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object object) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(serverSocketChannel.socket().accept().getOutputStream());
            oos.writeObject(object);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            while (true) {
                SocketChannel sChannel = SocketChannel.open();
                sChannel.configureBlocking(true);
                if (sChannel.connect(new InetSocketAddress("localhost", 2223))) {
                    ObjectInputStream ois = new ObjectInputStream(sChannel.socket().getInputStream());

                    Object object = ois.readObject();

                    //TODO: Handle Object and run Code with a Service Code Map
                    System.out.println("Connected");
                    System.out.println("Object: " + object);
                }
            }
        } catch (Exception e) {
            System.out.println("Es wurde nichts empfangen");
        }
    }
}
