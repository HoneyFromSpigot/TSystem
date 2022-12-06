package io.github.thewebcode.tsystem.server;

import java.io.Serializable;

public class IAction implements Serializable {
    private String className;
    private String methodName;
    private Object givenSource;
    private SourceType type;


    public IAction(String className, String methodName, SourceType type) {
        this.className = className;
        this.methodName = methodName;
        this.type = type;
    }

    public void setSource(Object source) {
        this.givenSource = source;
    }

    public SourceType getSourceType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object getGivenSource() {
        return givenSource;
    }
}
