package br.com.taok.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.taok.model.Lancamento;

public class LancamentoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Lancamento> listaPorFiltro(){
		
		System.out.println("Retorna tudo");
		
		return new ArrayList<>();
	}
}
