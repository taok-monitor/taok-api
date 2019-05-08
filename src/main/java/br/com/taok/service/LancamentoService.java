package br.com.taok.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.taok.dao.LancamentoDao;
import br.com.taok.exception.ServiceException;
import br.com.taok.model.Lancamento;
import br.com.taok.util.Transactional;

public class LancamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoDao dao;
	
	@Inject
	private EntityManager em;
	
	@Transactional
	public void salva(List<Lancamento> lancamentos) throws ServiceException {

		int contador = 0;
		for(Lancamento lancamento : lancamentos  ) {
			
			em.createNativeQuery("insert into public.lancamento(id_municipio, identificador, orgao, data_lancamento, valor, cpfcnpj_favorecido, nome_favorecido)values( ?,?,?,?,?,?,? )")
			.setParameter(1, lancamento.getMunicipio())
			.setParameter(2, lancamento.getIdentificador())
			.setParameter(3, lancamento.getOrgao())
			.setParameter(4, lancamento.getData())
			.setParameter(5, lancamento.getValor())
			.setParameter(6, lancamento.getCpfcnpjFavorecido())
			.setParameter(7, lancamento.getNomeFavorecido())
			.executeUpdate();
			
			if( contador % 100 == 0 ) {

				em.flush();
				em.clear();
			}
			
			contador++;
		}
		System.out.println("Salva");
	}
	
	public List<Lancamento> obtemTodos(){
		
		return dao.listaPorFiltro();
	}
}
