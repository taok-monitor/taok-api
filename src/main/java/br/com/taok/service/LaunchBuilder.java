package br.com.taok.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.taok.model.Launcher;

public class LaunchBuilder {

	private Launcher lancamento;
	
	/**
	 * 
	 * Criar uma nova instancia de {@link Launcher}
	 * 
	 * */
	public LaunchBuilder cria() {
		
		this.lancamento = new Launcher();
		
		return this;
	}
	
	public LaunchBuilder comMunicipio(Integer municipio) {
		
		this.lancamento.setMunicipio( municipio );
		return this;
	}
	
	public LaunchBuilder comIdentificador(String identificador) {
		
		this.lancamento.setIdentificador(identificador.replace("\"", ""));
		return this;
	}
	
	public LaunchBuilder comOrgao(String orgao) {
		
		this.lancamento.setOrgao(orgao.replace("\"", ""));
		return this;
	}
	
	public LaunchBuilder comData(String data, String padrao) {
		
		try {
		
			Date dataLancamento = new SimpleDateFormat(padrao).parse(data.replace("\"", ""));
			
			this.lancamento.setData(dataLancamento);
			return this;
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public LaunchBuilder comValor(String valor) {
		
		valor = valor.replace("\"", "").replace(".", "").replaceAll(",", ".");
		
		this.lancamento.setValor( new BigDecimal(valor));
		return this;
	}
	
	public LaunchBuilder comCpfCnpjDoFavorecido(String cpfcnpj) {
		
		this.lancamento.setCpfcnpjFavorecido(cpfcnpj.replace("\"", ""));
		return this;
	}
	
	public LaunchBuilder comNomeDoFavorecido(String nomeFavorecido) {
		
		this.lancamento.setNomeFavorecido(nomeFavorecido.replace("\"", ""));
		return this;
	}
	
	
	/**
	 * 
	 * Retorna a instancia criada no método <b>cria()</b>
	 * */
	public Launcher agora() {
		
		return this.lancamento;
	}
	
}
