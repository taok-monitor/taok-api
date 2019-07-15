package br.com.taok.carga;

import br.com.taok.exception.carga.DataLoadException;

public interface DataLoad {

	public void atualiza() throws DataLoadException;
}
