package io.github.thewebcode.tsystem.server;

import java.io.Serializable;

public class IGetAction extends IAction implements Serializable {
    private String serviceID;

    public IGetAction(String className, String methodName, SourceType type, String serviceID) {
        super(className, methodName, type);
        this.serviceID = serviceID;
    }

    public String getServiceID() {
        return serviceID;
    }
}
