package br.com.taok.service.consumidor;

import br.com.taok.model.Municipio;

public class ConsumidorAPITest {

	public static void main(String[] args) {
		
		Municipio fortaleza = new Municipio();
		fortaleza.setId(1);
		fortaleza.setDescricao("Prefeitura de Fortaleza");
		fortaleza.setUf("CE");
		
		fortaleza.getImplementacao().importa();
	}
}
