package br.com.taok.service.importation;

import org.junit.Test;

import br.com.taok.exception.importation.ImportationException;
import br.com.taok.service.importation.CeFortalezaImporter;
import br.com.taok.service.importation.Importer;

public class ImporterTest {

	@Test(expected=ImportationException.class)
	public void deveRetornarExcecao() {
		
		Importer importador = new CeFortalezaImporter();
		importador.makeImport();
	}
}
