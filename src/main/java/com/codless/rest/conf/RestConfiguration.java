package com.codless.rest.conf;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/service/")
public class RestConfiguration extends ResourceConfig {
	
    public RestConfiguration() {    
        packages(false, "com.codless.rest.resources");
        register(FilterCROS.class);
    }
}
