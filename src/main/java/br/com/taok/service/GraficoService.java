package br.com.taok.service;

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
	
	public List<Grafico> criaGraficoDeConsumo(Date dataInicial, Date dataFinal){
		
		List<Object[]> orgaosQueMaisConsumiram = dao.orgaosQueMaisConsumiran(dataInicial, dataFinal);
		criador.comDados(orgaosQueMaisConsumiram);

		return  Arrays.asList(criador.cria()); 	
	}
	
	public List<Grafico> criaGraficoComTotais(Date dataInicial, Date dataFinal){
		
		List<Object[]> dados = dao.totalConsumidoNoPeriodo(dataInicial, dataFinal);
		criador.comDados(dados);
		
		return  Arrays.asList( criador.cria() ); 	
	}
}
