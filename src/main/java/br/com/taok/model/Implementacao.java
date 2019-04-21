package br.com.taok.model;

import br.com.taok.service.importa.Importador;
import br.com.taok.service.importa.ImportadorCeFortaleza;

public enum Implementacao {

	CE_FORTALEZA(1, new ImportadorCeFortaleza());
	
	private Integer id;
	private Importador implementacao;
	
	Implementacao(Integer id, Importador as){
		
		this.id = id;
		this.implementacao = as;
	}
	
	public Integer getId() {
		
		return this.id;
	}
	
	public void importa() {
		
		implementacao.importa();
	}
	
	public static Implementacao obterImplementacaoPorId(Integer id){
		
		for( Implementacao implementacaoAtual : Implementacao.values() ) {

			if( implementacaoAtual.getId() == id ) {
				
				return implementacaoAtual;
			}
		}
		
		return null;
	}
}
