package br.com.taok.load;

import br.com.taok.exception.load.DataLoadException;

public interface DataLoad {

	public void atualiza() throws DataLoadException;
}
