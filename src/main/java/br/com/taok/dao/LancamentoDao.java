package br.com.taok.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.taok.model.Lancamento;
import br.com.taok.util.Transactional;

public class LancamentoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public List<Lancamento> listaPorFiltro(){
		
		System.out.println("Retorna tudo");
		
		return new ArrayList<>();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> orgaosQueMaisConsumiran(Date dataInicial, Date dataFinal){
		
		return em.createNativeQuery(" select" + 
									"   public.lancamento.orgao," + 
									"   sum( public.lancamento.valor )" + 
									" from" + 
									"   public.lancamento" + 
									" where" + 
									"   cast( public.lancamento.data_lancamento  as date) between :dataInicial and :dataFinal" + 
									" group by" + 
									"   public.lancamento.orgao" + 
									" order by" + 
									"   2 desc" + 
									" limit" + 
									"   10")
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.getResultList();
	}
}
