package br.com.taok.rest.conf;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class RestConfiguration extends ResourceConfig {
	
    public RestConfiguration() {    
        packages(false, "br.com.taok.rest.resources");
        register(FilterCROS.class);
    }
}
