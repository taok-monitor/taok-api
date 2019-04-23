package br.com.taok.service.importa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.taok.dao.MunicipioDao;
import br.com.taok.model.Lancamento;
import br.com.taok.model.Municipio;
import br.com.taok.service.LancamentoBuilder;
import br.com.taok.service.conector.ConectorAPI;

public class ImportadorCeFortaleza implements Importador {

	@Inject
	private MunicipioDao dao;
	
	private final String URL_DEFAULT = "https://transparencia.fortaleza.ce.gov.br/index.php/despesa/exibirResultConsultaPeriodoCSV/Companhia+De+Agua+E+Esgoto+Do+Ceara-Cagece/P/:datainicial/:datafinal/0/2019/RGVzcGVzYXMgZGEgUHJlZmVpdHVyYSBkZSBGb3J0YWxlemEgZGUgMDEvMDEvMjAxOSBhIDMxLzAxLzIwMTkgLSBGYXNlIGRlIFBhZ2FtZW50bw%3D%3D";
	
	@Override
	public Integer importa() {
		String url = URL_DEFAULT.replace(":datainicial", "01012019").replace(":datafinal", "31012019");
		
		List<String[]> dados = ConectorAPI.conecta(url);
		converteParaLancamentos(dados);
		
		System.out.println("Importou Fortaleza");
		
		return dados.size();
	}
	
	private List<Lancamento> converteParaLancamentos(List<String[]> dados ){
	
		Municipio fortaleza = dao.obterMunicipioPorId(1);
		List<Lancamento> retorno = new ArrayList<>();

		int contador = 0;
		
		for( String[] dado : dados ) {
			
			if( contador > 3 ) {
				
				Lancamento lacamento = new LancamentoBuilder().cria()
						.comIdentificador(dado[1])
						.comMunicipio(fortaleza)
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
