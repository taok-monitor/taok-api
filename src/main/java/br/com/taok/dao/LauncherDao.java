package br.com.taok.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.taok.model.Launcher;
import br.com.taok.util.Transactional;
import br.com.taok.util.Util;

public class LauncherDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public List<Launcher> listaPorFiltro(){
		
		System.out.println("Retorna tudo");
		
		return new ArrayList<>();
	}
	
	@Transactional
	public void remover(Date dataInicial, Date dataFinal){
		
		em.createNativeQuery(" delete from " + 
						"  public.lancamento " + 
						"where " + 
						"  cast( public.lancamento.data_lancamento as date) between :dataInicial and :dataFinal")
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.executeUpdate();
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
									"   5")
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> consumoPorOrgaos(Date dataInicial, Date dataFinal, List<String> orgaos){
		
		return em.createNativeQuery(" select" + 
									"   public.lancamento.orgao," + 
									"   sum( public.lancamento.valor )" + 
									" from" + 
									"   public.lancamento" + 
									" where" + 
									"   cast( public.lancamento.data_lancamento  as date) between :dataInicial and :dataFinal " +
									"   and public.lancamento.orgao in :orgaos " +
									" group by" + 
									"   public.lancamento.orgao" + 
									" order by" + 
									"   2 desc" + 
									" limit" + 
									"   5")
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.setParameter("orgaos", orgaos)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> totalConsumidoNoPeriodo(Date dataInicial, Date dataFinal){
		
		return em.createNativeQuery("	select " + 
										"  cast( extract( month from public.lancamento.data_lancamento) as int)as mes, " + 
										"  sum( public.lancamento.valor ) " + 
										"from " + 
										"  public.lancamento " + 
										"where " + 
										"  public.lancamento.data_lancamento between :dataInicial and :dataFinal " + 
										"group by " + 
										"  extract( month from public.lancamento.data_lancamento) " + 
										"order by" + 
										"  1")
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> totalConsumidoNoPeriodoPorOrgao(Date dataInicial, Date dataFinal, List<String> orgaos){
		
		return em.createNativeQuery("	select " + 
										"  cast( extract( month from public.lancamento.data_lancamento) as int)as mes, " + 
										"  sum( public.lancamento.valor ) " + 
										"from " + 
										"  public.lancamento " + 
										"where " + 
										"  public.lancamento.data_lancamento between :dataInicial and :dataFinal " + 
										"  and public.lancamento.orgao in :orgao " + 
										"group by " + 
										"  extract( month from public.lancamento.data_lancamento) " + 
										"order by" + 
										"  1")
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.setParameter("orgao", orgaos)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> consumoPorMes(Date data){
		
		return em.createNativeQuery("select " + 
				"  cast( extract( month from public.lancamento.data_lancamento ) as int)||'-'||cast( extract( year from public.lancamento.data_lancamento ) as int) as mes_importacao, " + 
				"  public.lancamento.orgao, " + 
				"  sum( public.lancamento.valor ) " + 
				"from " + 
				"  public.lancamento " + 
				"where " + 
				"  extract( year from public.lancamento.data_lancamento ) = :ano " + 
				"  and public.lancamento.orgao in  " + 
				"  (   " + 
				"  	select " + 
				"	  dados.orgao " + 
				"	from " + 
				"	  ( " + 
				"		select " + 
				"		  public.lancamento.orgao, " + 
				"		  sum( public.lancamento.valor ) " + 
				"		from " + 
				"		  public.lancamento " + 
				"		where " + 
				"		  extract( year from public.lancamento.data_lancamento ) = :ano " + 
				"		group by " + 
				"		  public.lancamento.orgao " + 
				"		order by " + 
				"		  2 desc " + 
				"		limit " + 
				"		  5   " + 
				"	  ) as dados " + 
				"   " + 
				"  )  " + 
				"group by " + 
				"  cast( extract( month from public.lancamento.data_lancamento ) as int),cast( extract( year from public.lancamento.data_lancamento ) as int),1,2 " + 
				"order by " + 
				"  cast( extract( month from public.lancamento.data_lancamento ) as int),2, 3 desc")
										.setParameter("ano", Util.asLocalDate(data).getYear() )
										.getResultList();
	}
	
}
