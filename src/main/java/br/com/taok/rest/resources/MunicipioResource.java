package br.com.taok.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.taok.dao.MunicipioDao;
import br.com.taok.rest.conf.ControllerRest;


@Path("/municipios")
@ControllerRest
public class MunicipioResource {

	@Inject
	private MunicipioDao municipioDao;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response municios() {        
     
    	return Response.ok( municipioDao.obtemTodosOsMunicipios() ).build();
    }
}
