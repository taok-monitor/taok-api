package br.com.taok.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.taok.model.grafico.Grafico;

public class CriadorDeGraficosBarra {

	private List<Object[]> dados = new ArrayList<>();

	public CriadorDeGraficosBarra comDados(List<Object[]> dados) {
		this.dados = dados;
		
		return this;
	}

	public Grafico cria() {
		
		List<String> labels = new ArrayList<>();
		List<BigDecimal> valores = new ArrayList<>();
		for( Object[] linha : dados ) {
			
			String label = (String) linha[0].toString();
			BigDecimal valor = (BigDecimal) linha[1];
			
			labels.add(label);
			valores.add(valor);
		}
		return new Grafico("Órgãos que mais consumiram", labels, valores);
	}
}
