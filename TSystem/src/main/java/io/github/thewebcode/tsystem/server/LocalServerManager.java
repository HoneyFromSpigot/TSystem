package io.github.thewebcode.tsystem.server;

import io.github.thewebcode.tsystem.TBungeeSystem;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class LocalServerManager {
    private ServerSocketChannel serverSocketChannel;

    private int port = 2223;
    public void receive() {
        try {
            while (true) {
                SocketChannel sChannel = SocketChannel.open();
                sChannel.configureBlocking(true);

                if (sChannel.connect(new InetSocketAddress("localhost", port))) {
                    ObjectInputStream ois = new ObjectInputStream(sChannel.socket().getInputStream());

                    Object object = ois.readObject();
                    if (object instanceof IAction) {
                        IAction action = (IAction) object;

                        if(action instanceof IGetAction) {
                            IGetAction getAction = (IGetAction) action;
                            Class<?>  clazz = Class.forName(getAction.getClassName());
                            Method method = clazz.getMethod(getAction.getMethodName());

                            Object source = null;

                            switch (action.getSourceType()) {
                                case NEW_INSTANCE:
                                    source = clazz.newInstance();
                                    break;
                                case GIVEN_INSTANCE:
                                    source = getAction.getGivenSource();
                                    break;
                                case MODULE_INSTANCE:
                                    source = TBungeeSystem.getInstance().getApi().getModule((String) getAction.getGivenSource());
                                    break;
                                case USE_API:
                                    source = TBungeeSystem.getInstance().getApi();
                                    break;
                            }

                            if (source == null) {
                                System.out.println("Source is null");
                                throw new IllegalStateException("Source is null");
                            }
                            Object returned = method.invoke(source);
                            IGetableObject getableObject = new IGetableObject(getAction.getServiceID(), returned);
                            TBungeeSystem.getInstance().getApi().sendToServer(getableObject);
                        }else {
                            Class<?> clazz = Class.forName(action.getClassName());
                            Method[] methods = clazz.getMethods();
                            Method method = clazz.getMethod(action.getMethodName());

                            Object source = null;

                            switch (action.getSourceType()) {
                                case NEW_INSTANCE:
                                    source = clazz.newInstance();
                                    break;
                                case GIVEN_INSTANCE:
                                    source = action.getGivenSource();
                                    break;
                                case MODULE_INSTANCE:
                                    source = TBungeeSystem.getInstance().getApi().getModule((String) action.getGivenSource());
                                    break;
                                case USE_API:
                                    source = TBungeeSystem.getInstance().getApi();
                                    break;
                            }

                            if (source == null) {
                                throw new IllegalStateException("Source is null");
                            }

                            method.invoke(source);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
