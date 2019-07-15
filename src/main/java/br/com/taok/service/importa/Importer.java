package br.com.taok.service.importa;

import br.com.taok.exception.importa.ImportationException;

public interface Importer {

	public void importa() throws ImportationException;
}
