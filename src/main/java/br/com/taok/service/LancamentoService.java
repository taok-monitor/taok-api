package br.com.taok.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.taok.dao.LancamentoDao;
import br.com.taok.exception.ServiceException;
import br.com.taok.model.Lancamento;

public class LancamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoDao dao;
	
	public void salva(List<Lancamento> lancamentos) throws ServiceException {
		
		System.out.println("Salva");
	}
	
	public List<Lancamento> obtemTodos(){
		
		return dao.listaPorFiltro();
	}
}
