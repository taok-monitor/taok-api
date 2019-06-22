package br.com.taok.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.taok.dao.LancamentoDao;
import br.com.taok.exception.ServiceException;
import br.com.taok.model.Lancamento;
import br.com.taok.post.CriadorDePost;
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
	
	@Transactional
	public void remover( Date dataInicial, Date dataFinal ) throws ServiceException {

		dao.remover(dataInicial, dataFinal);
	}
	
	public List<Lancamento> obtemTodos(){
		
		return dao.listaPorFiltro();
	}

	public void postagens(List<Lancamento> lancamentos) {

		Map<String, List<Lancamento>> agrupamentoPorOrgao = lancamentos.stream().collect(Collectors.groupingBy( Lancamento::getOrgao ));
		Map<String, BigDecimal> agrupamentoValorTotal = new HashMap<>();

		BigDecimal maiorValor = BigDecimal.ZERO;
		String maiorOrgao = "";
		
		for( String orgao: agrupamentoPorOrgao.keySet() ) {
			
			BigDecimal valorTotal = agrupamentoPorOrgao.get(orgao).stream().map( l -> l.getValor() ).reduce(BigDecimal.ZERO, BigDecimal::add);
			agrupamentoValorTotal.put(orgao, valorTotal);
		}
		
		for( String orgao : agrupamentoValorTotal.keySet() ) {
			
			BigDecimal valorDoOrgao = agrupamentoValorTotal.get(orgao);
			if( valorDoOrgao.compareTo(maiorValor) > 0 ) {
				
				maiorOrgao = orgao;
				maiorValor = valorDoOrgao;
			}
		}
		
		String mensagem = "Olá, gostaria de Informar a todos o Órgão Municipal de fortaleza que pagou o maior valor a CAGECE, conheça o: "+maiorOrgao
				+" Pagou "+NumberFormat.getCurrencyInstance().format(maiorValor)+" ";

		CriadorDePost.postar(mensagem);
	}
}
