package br.com.taok.service.consumidor;

import java.util.List;

import br.com.taok.model.Lancamento;
import br.com.taok.model.Municipio;

public class ConsumidorAPIDefault implements ConsumidorAPI {

	@Override
	public List<Lancamento> consome(Municipio municipio) {

		municipio.getImplementacao().importa();
		
		return null;
	}
}
