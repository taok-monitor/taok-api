package br.com.taok.rest.resources;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.taok.model.grafico.Chart;
import br.com.taok.rest.conf.ControllerRest;
import br.com.taok.service.ChartService;
import br.com.taok.util.Util;


@Path("/inicial")
@ControllerRest
public class ChartResource {

	@Inject
	private ChartService service;
	
	@Path("/{mesAnoInicial}/{mesAnoFinal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@PathParam("mesAnoInicial") String mesAnoInicial, @PathParam("mesAnoFinal") String mesAnoFinal, @QueryParam("orgao")List<String> orgaos ) {        
    	
		Integer mesInicial = Integer.parseInt( mesAnoInicial.substring(0,2) );
		Integer anoInicial = Integer.parseInt( mesAnoInicial.substring(2,6) );
		
		Integer mesFinal = Integer.parseInt( mesAnoFinal.substring(0,2) );
		Integer anoFinal = Integer.parseInt( mesAnoInicial.substring(2,6) );
		
		LocalDate dataInicial = LocalDate.of(anoInicial, mesInicial , 1);
		LocalDate dataFinal = LocalDate.now().withYear(anoFinal).withMonth(mesFinal).with(TemporalAdjusters.lastDayOfMonth());
		
    	List<Chart> graficos = service.criaGraficoDeConsumo( Util.asDate(dataInicial), Util.asDate(dataFinal), orgaos );
    	return Response.ok( graficos ).build();
    }
	
	@Path("/ano/{mesAnoInicial}/{mesAnoFinal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response processoAnual(@PathParam("mesAnoInicial") String mesAnoInicial, @PathParam("mesAnoFinal") String mesAnoFinal,  @QueryParam("orgao")List<String> orgaos ) {        
    	
		Integer mesInicial = Integer.parseInt( mesAnoInicial.substring(0,2) );
		Integer anoInicial = Integer.parseInt( mesAnoInicial.substring(2,6) );
		
		Integer mesFinal = Integer.parseInt( mesAnoFinal.substring(0,2) );
		Integer anoFinal = Integer.parseInt( mesAnoInicial.substring(2,6) );
		
		LocalDate dataInicial = LocalDate.of(anoInicial, mesInicial , 1);
		LocalDate dataFinal = LocalDate.now().withYear(anoFinal).withMonth(mesFinal).with(TemporalAdjusters.lastDayOfMonth());
		
    	List<Chart> graficos = service.criaGraficoComTotais( Util.asDate(dataInicial), Util.asDate(dataFinal), orgaos );
    	return Response.ok( graficos ).build();
    }
}
