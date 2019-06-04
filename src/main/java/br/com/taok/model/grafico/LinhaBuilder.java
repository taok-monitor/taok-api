package br.com.taok.model.grafico;

import java.util.List;

public class LinhaBuilder {

	private String titulo;
	private List<Object[]> valores;

	public LinhaBuilder(String titulo, List<Object[]> valores) {
		this.titulo = titulo;
		this.valores = valores;
	}
}
