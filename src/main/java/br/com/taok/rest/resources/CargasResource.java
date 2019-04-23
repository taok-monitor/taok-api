package br.com.taok.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.taok.carga.AtualizadorDeCargas;
import br.com.taok.rest.conf.ControllerRest;


@Path("/cargas")
@ControllerRest
public class CargasResource {

	@Inject
	private AtualizadorDeCargas atualizador;
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response carregar() {        
     
    	atualizador.atualiza();
    	return Response.ok( "ok" ).build();
    }
}
