package br.com.taok.service.importa;

import br.com.taok.exception.importa.ImportadorException;

public interface Importador {

	public void importa() throws ImportadorException;
}
