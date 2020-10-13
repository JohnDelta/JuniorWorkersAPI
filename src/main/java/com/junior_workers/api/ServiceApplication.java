package com.junior_workers.api;

import java.util.HashSet;
import java.util.Set;

import com.junior_workers.filter.CORSFilter;
import com.junior_workers.services.*;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class ServiceApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(UserService.class);
        classes.add(SearchService.class);
        classes.add(ModelService.class);
        classes.add(CORSFilter.class);
        return classes;
    }
}
