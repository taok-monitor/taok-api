package br.com.taok.carga;

import br.com.taok.carga.exception.AtualizadorDeCargasException;

public interface AtualizadorDeCargas {

	public void atualiza() throws AtualizadorDeCargasException;
}
