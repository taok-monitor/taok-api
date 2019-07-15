package br.com.taok.exception.importa;

public class ImportationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImportationException(String menssagem ) {

		super(menssagem);
	}
}
