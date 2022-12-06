package io.github.thewebcode.tsystem.server;


import java.io.Serializable;

public class IGetableObject implements Serializable {
    private String serviceID;
    private Object returningObject;

    public IGetableObject(String serviceID, Object returningObject) {
        this.serviceID = serviceID;
        this.returningObject = returningObject;
    }

    public String getServiceID() {
        return serviceID;
    }

    public Object getObject() {
        return returningObject;
    }
}
