package br.com.taok.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.taok.rest.conf.ControllerRest;


@Path("/")
@ControllerRest
public class IndexResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {        
     
    	return Response.ok("{ \"title\":\"system up\"}").build();
    }
}
