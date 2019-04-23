package br.com.taok.service.importa;

import org.junit.Test;

import br.com.taok.exception.importa.ImportadorException;
import br.com.taok.service.importa.Importador;
import br.com.taok.service.importa.ImportadorCeFortaleza;

public class ImportadorTest {

	@Test(expected=ImportadorException.class)
	public void deveRetornarExcecao() {
		
		Importador importador = new ImportadorCeFortaleza();
		importador.importa();
	}
}
