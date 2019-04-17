package com.codless.exception;

/**
 * 
 * This object can representation for errors in requests 
 * 
 * */
public class ExceptionDefault {

	private String mensagem;
	private String title;

	public ExceptionDefault(String mensagem) {
		this.title = "Error";
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getTitle() {
		return title;
	}
}
