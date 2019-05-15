package br.com.taok.service;

import java.math.BigDecimal;
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
	
	public List<Grafico> criaGrafico(Date dataInicial, Date dataFinal){
		
		List<Object[]> orgaosQueMaisConsumiram = dao.orgaosQueMaisConsumiran(dataInicial, dataFinal);
		
		List<String> labels = new ArrayList<>();
		List<BigDecimal> valores = new ArrayList<>();
		
		for( Object[] linha : orgaosQueMaisConsumiram ) {
			
			String label = (String) linha[0];
			BigDecimal valor = (BigDecimal) linha[1];
			
			labels.add(label);
			valores.add(valor);
		}

		return  Arrays.asList(new Grafico("Órgãos que mais consumiram", labels, valores)); 	
	}
}
