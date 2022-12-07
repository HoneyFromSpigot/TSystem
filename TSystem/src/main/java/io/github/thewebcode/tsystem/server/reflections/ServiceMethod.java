package io.github.thewebcode.tsystem.server.reflections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMethod {
    String serviceID();
}
