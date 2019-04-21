package br.com.taok.carga;

import java.util.List;

import javax.inject.Inject;

import br.com.taok.carga.exception.AtualizadorDeCargasException;
import br.com.taok.dao.MunicipioDao;
import br.com.taok.model.Municipio;

public class AtualizadorDeCargasDefault implements AtualizadorDeCargas {

	@Inject
	private MunicipioDao municipioDao;
	
	@Override
	public void atualiza() throws AtualizadorDeCargasException {

		List<Municipio> municipios = municipioDao.obtemTodosOsMunicipios();
		
		for (Municipio municipio : municipios) {

			municipio.carregaDados();
		}
	}
}
