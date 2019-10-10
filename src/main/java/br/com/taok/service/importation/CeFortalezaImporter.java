package br.com.taok.service.importation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.taok.exception.importation.ImportationException;
import br.com.taok.model.Launcher;
import br.com.taok.service.LaunchBuilder;
import br.com.taok.service.LaunchService;
import br.com.taok.service.conector.ConectorAPI;
import br.com.taok.util.Util;

public class CeFortalezaImporter implements Importer {

	@Inject
	private LaunchService service;
	
	private final String URL_DEFAULT = "https://transparencia.fortaleza.ce.gov.br/index.php/despesa/exibirResultConsultaPeriodoCSV/Companhia+De+Agua+E+Esgoto+Do+Ceara-Cagece/P/:datainicial/:datafinal/0/2019/RGVzcGVzYXMgZGEgUHJlZmVpdHVyYSBkZSBGb3J0YWxlemEgZGUgMDEvMDEvMjAxOSBhIDMxLzAxLzIwMTkgLSBGYXNlIGRlIFBhZ2FtZW50bw%3D%3D";
	
	@Override
	public void makeImport() {

		for(int month =1; month <=12; month++) {
			
			LocalDate startDate = LocalDate.of(2019, month, 1);
			LocalDate endDate;
			
			if( month == 12 ) {
			
				endDate = LocalDate.of(2019, month, 31);
			}else {
				
				endDate = LocalDate.of(2019, month+1, 1).minusDays(1);
			}
			
			String diaInicial = startDate.getDayOfMonth() < 10 ? "0"+startDate.getDayOfMonth(): ""+startDate.getDayOfMonth();
			String mesInicial = startDate.getMonthValue()<10 ? "0"+startDate.getMonthValue(): ""+startDate.getMonthValue();
			String dataInicialFiltro = diaInicial+mesInicial+""+startDate.getYear();

			String diaFinal = endDate.getDayOfMonth() < 10 ? "0"+endDate.getDayOfMonth(): ""+endDate.getDayOfMonth();
			String mesFinal = endDate.getMonthValue()<10 ? "0"+endDate.getMonthValue(): ""+endDate.getMonthValue();
			String dataFinalFiltro = diaFinal+mesFinal+""+endDate.getYear();
			
			try {
				
				String url = URL_DEFAULT.replace(":datainicial", dataInicialFiltro).replace(":datafinal", dataFinalFiltro);
				List<String[]> data = ConectorAPI.conecta(url);
				List<Launcher> launchers = normalizeData(data);
				
				service.remover( Util.asDate(startDate), Util.asDate(endDate));
				
				service.save(launchers);
				System.out.println("Fortaleza/CE Improted");
			} catch (Exception e) {

				throw new ImportationException("Importation form Fortaleza/CE, Failed: "+e.getMessage());
			}
		}
	}
	
	private List<Launcher> normalizeData(List<String[]> dados ){
	
		List<Launcher> retorno = new ArrayList<>();

		int contador = 0;
		
		for( String[] dado : dados ) {
			
			if( contador > 3 ) {
				
				Launcher lacamento = new LaunchBuilder().cria()
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
