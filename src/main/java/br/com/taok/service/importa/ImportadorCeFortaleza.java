package br.com.taok.service.importa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.taok.exception.importa.ImportadorException;
import br.com.taok.model.Lancamento;
import br.com.taok.service.LancamentoBuilder;
import br.com.taok.service.LancamentoService;
import br.com.taok.service.conector.ConectorAPI;
import br.com.taok.util.Util;

public class ImportadorCeFortaleza implements Importador {

	@Inject
	private LancamentoService service;
	
	private final String URL_DEFAULT = "https://transparencia.fortaleza.ce.gov.br/index.php/despesa/exibirResultConsultaPeriodoCSV/Companhia+De+Agua+E+Esgoto+Do+Ceara-Cagece/P/:datainicial/:datafinal/0/2019/RGVzcGVzYXMgZGEgUHJlZmVpdHVyYSBkZSBGb3J0YWxlemEgZGUgMDEvMDEvMjAxOSBhIDMxLzAxLzIwMTkgLSBGYXNlIGRlIFBhZ2FtZW50bw%3D%3D";
	
	@Override
	public void importa() {

		for(int mes =1; mes <=12; mes++) {
			
			LocalDate dataInicial = LocalDate.of(2019, mes, 1);
			LocalDate dataFinal;
			
			if( mes == 12 ) {
			
				dataFinal = LocalDate.of(2019, mes, 31);
			}else {
				
				dataFinal = LocalDate.of(2019, mes+1, 1).minusDays(1);
			}
			
			String diaInicial = dataInicial.getDayOfMonth() < 10 ? "0"+dataInicial.getDayOfMonth(): ""+dataInicial.getDayOfMonth();
			String mesInicial = dataInicial.getMonthValue()<10 ? "0"+dataInicial.getMonthValue(): ""+dataInicial.getMonthValue();
			String dataInicialFiltro = diaInicial+mesInicial+""+dataInicial.getYear();

			String diaFinal = dataFinal.getDayOfMonth() < 10 ? "0"+dataFinal.getDayOfMonth(): ""+dataFinal.getDayOfMonth();
			String mesFinal = dataFinal.getMonthValue()<10 ? "0"+dataFinal.getMonthValue(): ""+dataFinal.getMonthValue();
			String dataFinalFiltro = diaFinal+mesFinal+""+dataFinal.getYear();
			
			try {
				
				String url = URL_DEFAULT.replace(":datainicial", dataInicialFiltro).replace(":datafinal", dataFinalFiltro);
				List<String[]> dados = ConectorAPI.conecta(url);
				List<Lancamento> lancamentos = normalizaDados(dados);
				
				service.remover( Util.asDate(dataInicial), Util.asDate(dataFinal));
				service.salva(lancamentos);
				System.out.println("Importou Fortaleza");
			} catch (Exception e) {

				throw new ImportadorException("Problema para Importar: "+e.getMessage());
			}
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
