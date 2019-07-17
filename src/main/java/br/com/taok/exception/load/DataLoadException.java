package br.com.taok.exception.load;

public class DataLoadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataLoadException(String menssagem ) {

		super(menssagem);
	}
}
