package br.com.taok.service.importa;

import org.junit.Test;

import br.com.taok.exception.importa.ImportationException;
import br.com.taok.service.importa.Importer;
import br.com.taok.service.importa.CeFortalezaImporter;

public class ImporterTest {

	@Test(expected=ImportationException.class)
	public void deveRetornarExcecao() {
		
		Importer importador = new CeFortalezaImporter();
		importador.importa();
	}
}
