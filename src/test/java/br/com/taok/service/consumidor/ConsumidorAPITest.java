package br.com.taok.service.consumidor;

import org.junit.Assert;
import org.junit.Test;

import br.com.taok.model.Municipio;

public class ConsumidorAPITest {

	@Test
	public void deveImportarLancamentosDaPrefeituraDeFortaleza() {
		
		Municipio fortaleza = new Municipio();
		fortaleza.setId(1);
		fortaleza.setDescricao("Prefeitura de Fortaleza");
		fortaleza.setUf("CE");
		
		Integer importa = fortaleza.carregaDados();
		
		Assert.assertTrue(importa > 0);
	}
}
