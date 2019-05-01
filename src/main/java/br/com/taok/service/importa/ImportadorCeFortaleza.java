package br.com.taok.service.importa;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import br.com.taok.exception.importa.ImportadorException;
import br.com.taok.model.Lancamento;
import br.com.taok.service.LancamentoBuilder;
import br.com.taok.service.LancamentoService;
import br.com.taok.service.conector.ConectorAPI;

public class ImportadorCeFortaleza implements Importador {

	@Inject
	private LancamentoService service;
	
	private final String URL_DEFAULT = "https://transparencia.fortaleza.ce.gov.br/index.php/despesa/exibirResultConsultaPeriodoCSV/Companhia+De+Agua+E+Esgoto+Do+Ceara-Cagece/P/:datainicial/:datafinal/0/2019/RGVzcGVzYXMgZGEgUHJlZmVpdHVyYSBkZSBGb3J0YWxlemEgZGUgMDEvMDEvMjAxOSBhIDMxLzAxLzIwMTkgLSBGYXNlIGRlIFBhZ2FtZW50bw%3D%3D";
	
	@Override
	public void importa() {

		try {
		
			String url = URL_DEFAULT.replace(":datainicial", "01042019").replace(":datafinal", "30042019");

			NumberFormat format = NumberFormat.getCurrencyInstance();
			
			List<String[]> dados = ConectorAPI.conecta(url);
			List<Lancamento> lancamentos = normalizaDados(dados);
			
			service.salva(lancamentos);
			
			Map<String, List<Lancamento>> porOrgao = lancamentos.stream().collect( Collectors.groupingBy( Lancamento::getOrgao ) );
			
			System.out.println("-------------------------------------------------------------------------------");
			
			for( String orcao : porOrgao.keySet() ) {

				BigDecimal valorTotal = porOrgao.get(orcao).stream()
				.map(l -> l.getValor())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
				
				int totalDeLancamentos = porOrgao.get(orcao).size();
				
				System.out.println(orcao +" | Total de Lancamentos:"+ totalDeLancamentos + "| Valor Total pago:"+ format.format(valorTotal ));
				System.out.println("-------------------------------------------------------------------------------");
			}
			
			System.out.println("Importou Fortaleza");
		} catch (Exception e) {

			throw new ImportadorException("Problema para Importar: "+e.getMessage());
		}
	}
	
	private List<Lancamento> normalizaDados(List<String[]> dados ){
	
		List<Lancamento> retorno = new ArrayList<>();

		int contador = 0;
		
		for( String[] dado : dados ) {
			
			if( contador > 3 ) {
				
				Lancamento lacamento = new LancamentoBuilder().cria()
						.comIdentificador(dado[1])
						.comMunicipio(1)
						.comOrgao(dado[5])
						.comValor(dado[7])
						.comCpfCnpjDoFavorecido(dado[3])
						.comNomeDoFavorecido(dado[4])
						.comData(dado[0], "dd/MM/yyyy")
						.agora();
				
				retorno.add(lacamento);
			}
			
			contador++;
		}
		return retorno;
	}
}
