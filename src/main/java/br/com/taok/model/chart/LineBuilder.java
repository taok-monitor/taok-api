package br.com.taok.model.chart;

import java.util.List;

public class LineBuilder {

	private String titulo;
	private List<Object[]> valores;

	public LineBuilder(String titulo, List<Object[]> valores) {
		this.titulo = titulo;
		this.valores = valores;
	}
}
