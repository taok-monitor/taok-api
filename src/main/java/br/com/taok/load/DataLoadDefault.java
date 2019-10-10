package br.com.taok.load;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.taok.exception.load.DataLoadException;
import br.com.taok.service.importation.Importer;

public class DataLoadDefault implements DataLoad {

	@Inject
	@Any
	private Instance<Importer> importadoresLazy;
	
	@Override
	public void update() throws DataLoadException {
		
		for (Importer importer : importadoresLazy) {

			importer.makeImport();
		}
	}
}
