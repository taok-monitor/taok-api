package br.com.taok.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.taok.model.Lancamento;
import br.com.taok.model.Municipio;

public class LancamentoBuilder {

	private Lancamento lancamento;
	
	/**
	 * 
	 * Criar uma nova instancia de {@link Lancamento}
	 * 
	 * */
	public LancamentoBuilder cria() {
		
		this.lancamento = new Lancamento();
		
		return this;
	}
	
	public LancamentoBuilder comMunicipio(Municipio municipio) {
		
		this.lancamento.setMunicipio(municipio);
		return this;
	}
	
	public LancamentoBuilder comIdentificador(String identificador) {
		
		this.lancamento.setIdentificador(identificador);
		return this;
	}
	
	public LancamentoBuilder comOrgao(String orgao) {
		
		this.lancamento.setOrgao(orgao);
		return this;
	}
	
	public LancamentoBuilder comData(String data, String padrao) {
		
		try {
		
			Date dataLancamento = new SimpleDateFormat(padrao).parse(data.replace("\"", ""));
			
			this.lancamento.setData(dataLancamento);
			return this;
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public LancamentoBuilder comValor(String valor) {
		
		valor = valor.replace("\"", "").replace(".", "").replaceAll(",", ".");
		
		this.lancamento.setValor( new BigDecimal(valor));
		return this;
	}
	
	public LancamentoBuilder comCpfCnpjDoFavorecido(String cpfcnpj) {
		
		this.lancamento.setCpfcnpjFavorecido(cpfcnpj);
		return this;
	}
	
	public LancamentoBuilder comNomeDoFavorecido(String nomeFavorecido) {
		
		this.lancamento.setNomeFavorecido(nomeFavorecido);
		return this;
	}
	
	
	/**
	 * 
	 * Retorna a instancia criada no método <b>cria()</b>
	 * */
	public Lancamento agora() {
		
		return this.lancamento;
	}
	
}
