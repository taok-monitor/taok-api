package br.com.taok.service.conector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConectorAPI {

	/**
	 * 
	 * Classe responsável por conectar em uma api atraves de uma URL e rotornar um array de String, 
	 * essa api tem com premissa que retornar CSV separado por virgula
	 * 
	 * */
	public static List<String[]> conecta(String urlApi) {
		
		HttpURLConnection connection = null;
		List<String[]> retorno = new ArrayList<>();
		
		try {
			  
			URL url = new URL(urlApi);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length",Integer.toString("".length()));
		    connection.setRequestProperty("Content-Language", "pt-BR");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
		    wr.writeBytes("");
		    wr.close();

		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    String line;
		    while ((line = rd.readLine()) != null) {
		    	
		    	retorno.add(line.split(";"));
		    	
		    }
		    rd.close();
		    
		    return retorno;
			    
		}catch (Exception e) {
		    e.printStackTrace();
		    
		    return retorno;
		} finally {
			if (connection != null) {
				connection.disconnect();
		    }
		}
	}
}
