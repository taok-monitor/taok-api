package br.com.taok.carga;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.taok.exception.carga.AtualizadorDeCargasException;
import br.com.taok.service.importa.Importador;

public class AtualizadorDeCargasDefault implements AtualizadorDeCargas {

	@Inject
	@Any
	private Instance<Importador> importadoresLazy;
	
	@Override
	public void atualiza() throws AtualizadorDeCargasException {
		
		for (Importador importador : importadoresLazy) {

			importador.importa();
		}
	}
}
