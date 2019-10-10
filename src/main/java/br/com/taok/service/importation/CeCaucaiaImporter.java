package br.com.taok.service.importation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.taok.exception.importation.ImportationException;
import br.com.taok.model.Launcher;
import br.com.taok.service.LaunchBuilder;
import br.com.taok.service.conector.ConectorAPI;

public class CeCaucaiaImporter implements Importer {

	private final String URL_DEFAULT = "http://www.sstransparenciamunicipal.net/transparencia/pagamentocsv.php?entcod=2&dataini=:startDate&datafim=:endDate&campo=1PAGCREDOR&texto=Q0FHRUNF&uo=";
	
	@Override
	public void makeImport() {
	
		for(int month =1; month <=12; month++) {
			
			LocalDate startDate = LocalDate.of(2019, month, 1);
			LocalDate endDate = LocalDate.of(2019, month, startDate.lengthOfMonth() ); 
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			try {
				
				String url = URL_DEFAULT.replace(":startDate", startDate.format(formatter)).replace(":endDate", endDate.format(formatter));
				List<String[]> data = ConectorAPI.conecta(url);
				List<Launcher> launchers = normalizeData(data);
				
				for (Launcher launcher : launchers) {
					
					//TODO Import Caucaia
				}
				
			} catch (Exception e) {

				throw new ImportationException("Importation form Caucaia/CE, Failed: "+e.getMessage());
			}
		}
		
		System.out.println("Caucaia/CE Imported");
	}
	
	private List<Launcher> normalizeData(List<String[]> dados ){
		
		List<Launcher> retorno = new ArrayList<>();

		int contador = 0;
		
		for( String[] dado : dados ) {
			
			if( contador > 3 ) {
				
				Launcher lacamento = new LaunchBuilder().cria()
						.comIdentificador(dado[2])
						.comMunicipio(2)
						.comOrgao(dado[0])
						.comValor(dado[5])
						.comCpfCnpjDoFavorecido(dado[3])
						.comNomeDoFavorecido(dado[4])
						.comData(dado[1], "dd/MM/yyyy")
						.agora();
				
				retorno.add(lacamento);
			}
			
			contador++;
		}
		return retorno;
	}
}
