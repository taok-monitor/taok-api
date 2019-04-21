package br.com.taok.service.consumidor;

import java.util.List;

import br.com.taok.model.Lancamento;
import br.com.taok.model.Municipio;

public interface ConsumidorAPI {

	public List<Lancamento> consome(Municipio municipio);
}
