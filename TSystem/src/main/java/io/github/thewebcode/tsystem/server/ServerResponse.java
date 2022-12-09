package io.github.thewebcode.tsystem.server;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private Object object;
    private String id;

    public ServerResponse(Object object, String id) {
        this.object = object;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }
}
