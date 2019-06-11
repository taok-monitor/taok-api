package br.com.taok.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import br.com.taok.dao.LancamentoDao;
import br.com.taok.model.grafico.Grafico;

public class GraficoService {

	@Inject
	private LancamentoDao dao;
	
	private CriadorDeGraficosBarra criador = new CriadorDeGraficosBarra();
	
	public List<Grafico> criaGraficoDeConsumo(Date dataInicial, Date dataFinal, List<String> orgaos){
		
		List<Object[]> orgaosQueMaisConsumiram = new ArrayList<>();
		orgaos = orgaos.stream().filter( o -> o.trim().length() > 0 ).collect(Collectors.toList());
		
		if( orgaos.size() == 0  ) {
		
			orgaosQueMaisConsumiram = dao.orgaosQueMaisConsumiran(dataInicial, dataFinal);
		}else {
			
			orgaosQueMaisConsumiram = dao.consumoPorOrgaos(dataInicial, dataFinal, orgaos);
		}
		
		criador.comDados(orgaosQueMaisConsumiram);
		return  Arrays.asList(criador.cria()); 	
	}
	
	public List<Grafico> criaGraficoComTotais(Date dataInicial, Date dataFinal, List<String> orgaos){
		
		List<Object[]> dados = new ArrayList<>();
		orgaos = orgaos.stream().filter( o -> o.trim().length() > 0 ).collect(Collectors.toList());
		
		if( orgaos.size() == 0  ) {
			
		
			dados = dao.totalConsumidoNoPeriodo(dataInicial, dataFinal);
		}else {
			
			dados = dao.totalConsumidoNoPeriodoPorOrgao(dataInicial, dataFinal, orgaos);
		}

		criador.comDados(dados);
		
		return  Arrays.asList( criador.cria() ); 	
	}
}
