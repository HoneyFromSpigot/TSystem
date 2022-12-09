package io.github.thewebcode.tsystem.server;

import java.io.Serializable;

public class ServerRequest implements Serializable {
    private String serviceID;
    private Object[] args;
    private int returningPort;

    public ServerRequest(String serviceID) {
        this.serviceID = serviceID;
    }

    public void setReturningPort(int port){
        this.returningPort = port;
    }
    public void addArgs(Object[] args) {
        this.args = args;
    }

    public String getServiceID() {
        return serviceID;
    }


    public int getReturningPort() {
        return returningPort;
    }

    public Object[] getArgs() {
        return args;
    }
}
