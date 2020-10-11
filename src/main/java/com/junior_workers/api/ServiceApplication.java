package com.junior_workers.api;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class ServiceApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(Example.class);
        return classes;
    }
}
