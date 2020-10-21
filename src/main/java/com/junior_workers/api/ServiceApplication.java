package com.junior_workers.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        classes.add(MediaService.class);
        return classes;
    }
	
	@Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.classnames", "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return properties;
    }

}
