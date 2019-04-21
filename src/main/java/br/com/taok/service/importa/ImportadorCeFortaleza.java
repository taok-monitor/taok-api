package br.com.taok.service.importa;

import java.util.List;

import br.com.taok.service.conector.ConectorAPI;

public class ImportadorCeFortaleza implements Importador {

	private final String URL_DEFAULT = "https://transparencia.fortaleza.ce.gov.br/index.php/despesa/exibirResultConsultaPeriodoCSV/Companhia+De+Agua+E+Esgoto+Do+Ceara-Cagece/P/:datainicial/:datafinal/0/2019/RGVzcGVzYXMgZGEgUHJlZmVpdHVyYSBkZSBGb3J0YWxlemEgZGUgMDEvMDEvMjAxOSBhIDMxLzAxLzIwMTkgLSBGYXNlIGRlIFBhZ2FtZW50bw%3D%3D";
	
	@Override
	public Integer importa() {
		String url = URL_DEFAULT.replace(":datainicial", "01012019").replace(":datafinal", "31012019");
		
		List<String[]> conecta = ConectorAPI.conecta(url);
		
		for (String[] strings : conecta) {
			
			System.out.println(strings[0]);
		}
		
		return conecta.size();
	}
	
}
