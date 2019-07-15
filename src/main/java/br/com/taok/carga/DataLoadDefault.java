package br.com.taok.carga;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.taok.exception.carga.DataLoadException;
import br.com.taok.service.importa.Importer;

public class DataLoadDefault implements DataLoad {

	@Inject
	@Any
	private Instance<Importer> importadoresLazy;
	
	@Override
	public void atualiza() throws DataLoadException {
		
		for (Importer importador : importadoresLazy) {

			importador.importa();
		}
	}
}
