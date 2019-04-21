package br.com.taok.service.conector;

import org.junit.Assert;
import org.junit.Test;

import br.com.taok.service.importa.Importador;
import br.com.taok.service.importa.ImportadorCeFortaleza;

public class ConectorTest {

	@Test
	public void deveImportarPeloMenosUm() {
		
		Importador importador = new ImportadorCeFortaleza();
		Integer importa = importador.importa();
		
		Assert.assertTrue(importa > 0);
	}
}
