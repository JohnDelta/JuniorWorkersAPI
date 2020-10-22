package com.junior_workers.api;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.junior_workers.filter.CORSFilter;
import com.junior_workers.services.*;

import jakarta.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class ServiceApplication extends ResourceConfig {
	
	public ServiceApplication() {
		register(MultiPartFeature.class);
		register(UserService.class);
		register(SearchService.class);
		register(ModelService.class);
		register(CORSFilter.class);
		register(MediaService.class);	
	}
	
//	@Override
//    public Set<Class<?>> getClasses() {
//        final Set<Class<?>> classes = new HashSet<>();
//        classes.add(UserService.class);
//        classes.add(SearchService.class);
//        classes.add(ModelService.class);
//        classes.add(CORSFilter.class);
//        classes.add(MediaService.class);
//        return classes;
//    }
//	
//	@Override
//    public Map<String, Object> getProperties() {
//        final Map<String, Object> properties = new HashMap<>();
//        properties.put("jersey.config.server.provider.classnames", "org.glassfish.jersey.media.multipart.MultiPartFeature");
//        return properties;
//    }

}
