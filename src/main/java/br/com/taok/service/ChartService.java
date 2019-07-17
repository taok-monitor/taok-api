package br.com.taok.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import br.com.taok.dao.LaunchDao;
import br.com.taok.model.chart.Chart;

public class ChartService {

	@Inject
	private LaunchDao dao;
	
	private ChartBarCreator criador = new ChartBarCreator();
	
	public List<Chart> criaGraficoDeConsumo(Date dataInicial, Date dataFinal, List<String> orgaos){
		
		List<Object[]> orgaosQueMaisConsumiram = new ArrayList<>();
		orgaos = orgaos.stream().filter( o -> o.trim().length() > 0 ).collect(Collectors.toList());
		
		if( orgaos.size() == 0  ) {
		
			orgaosQueMaisConsumiram = dao.largestConsumers(dataInicial, dataFinal);
		}else {
			
			orgaosQueMaisConsumiram = dao.consuptionByPublicCompanys(dataInicial, dataFinal, orgaos);
		}
		
		criador.comDados(orgaosQueMaisConsumiram);
		return  Arrays.asList(criador.cria()); 	
	}
	
	public List<Chart> criaGraficoComTotais(Date dataInicial, Date dataFinal, List<String> orgaos){
		
		List<Object[]> dados = new ArrayList<>();
		orgaos = orgaos.stream().filter( o -> o.trim().length() > 0 ).collect(Collectors.toList());
		
		if( orgaos.size() == 0  ) {
			
		
			dados = dao.totalConsumedInPeriod(dataInicial, dataFinal);
		}else {
			
			dados = dao.totalConsumedInPeriodByPublicCompanys(dataInicial, dataFinal, orgaos);
		}

		criador.comDados(dados);
		
		return  Arrays.asList( criador.cria() ); 	
	}
}
