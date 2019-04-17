package com.codless.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codless.model.People;
import com.codless.rest.conf.ControllerRest;


@Path("/")
@ControllerRest
public class IndexResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {        
     
    	return Response.ok("{ \"title\":\"system up\"}").build();
    }
    
	@GET
    @Path("/exception")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exception() {        
     
    	People peopleToJson = null;
    	
    	System.out.println(peopleToJson.getLastName());
    	
    	return Response.ok("{ \"title\":\"system up\"}").build();
    }
    
    @GET
    @Path("/producesjson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response producesJson() {        
     
    	People peopleToJson = new People("Mattheus","Cassunde");
    	
    	return Response.ok(peopleToJson).build();
    }
    
    @POST
    @Path("/consumerjson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumerJson(People people) {        
     
    	System.out.println(people);
    	
    	return Response.status(200).entity(people).build();
    }
}
