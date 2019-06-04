package br.com.taok.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.taok.dao.LancamentoDao;
import br.com.taok.model.grafico.Grafico;

public class GraficoService {

	@Inject
	private LancamentoDao dao;
	
	private CriadorDeGraficosBarra criador = new CriadorDeGraficosBarra();
	
	public List<Grafico> criaGraficoDeConsumo(Date dataInicial, Date dataFinal, String orgao){
		
		List<Object[]> orgaosQueMaisConsumiram = new ArrayList<>();
		
		if( orgao == null ) {
		
			orgaosQueMaisConsumiram = dao.orgaosQueMaisConsumiran(dataInicial, dataFinal);
		}else {
			
			orgaosQueMaisConsumiram = dao.consumoPorOrgao(dataInicial, dataFinal, orgao);
		}
		
		
		criador.comDados(orgaosQueMaisConsumiram);

		return  Arrays.asList(criador.cria()); 	
	}
	
	public List<Grafico> criaGraficoComTotais(Date dataInicial, Date dataFinal, String orgao){
		
		List<Object[]> dados = new ArrayList<>();
		
		if( orgao == null ) {
			
		
			dados = dao.totalConsumidoNoPeriodo(dataInicial, dataFinal);
		}else {
			
			dados = dao.totalConsumidoNoPeriodoPorOrgao(dataInicial, dataFinal, orgao);
		}

		criador.comDados(dados);
		
		return  Arrays.asList( criador.cria() ); 	
	}
}
