package br.com.taok.carga;

import br.com.taok.exception.carga.AtualizadorDeCargasException;

public interface AtualizadorDeCargas {

	public void atualiza() throws AtualizadorDeCargasException;
}
