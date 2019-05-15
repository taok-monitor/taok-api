package br.com.taok.service.importa;

import java.time.LocalDate;

public class CriaDataTeste {

	
	
	public static void main(String[] args) {

		for(int mes =1; mes <=12; mes++) {
			
			LocalDate dataInicial = LocalDate.of(2018, mes, 1);
			LocalDate dataFinal;
			
			if( mes == 12 ) {
			
				dataFinal = LocalDate.of(2018, mes, 31);
			}else {
				
				dataFinal = LocalDate.of(2018, mes+1, 1).minusDays(1);
			}
			
			String diaInicial = dataInicial.getDayOfMonth() < 10 ? "0"+dataInicial.getDayOfMonth(): ""+dataInicial.getDayOfMonth();
			String mesInicial = dataInicial.getMonthValue()<10 ? "0"+dataInicial.getMonthValue(): ""+dataInicial.getMonthValue();
			String dataInicialFiltro = diaInicial+mesInicial+""+dataInicial.getYear();

			String diaFinal = dataFinal.getDayOfMonth() < 10 ? "0"+dataFinal.getDayOfMonth(): ""+dataFinal.getDayOfMonth();
			String mesFinal = dataFinal.getMonthValue()<10 ? "0"+dataFinal.getMonthValue(): ""+dataFinal.getMonthValue();
			String dataFinalFiltro = diaFinal+mesFinal+""+dataFinal.getYear();
			
			System.out.println(dataInicialFiltro);
			System.out.println(dataFinalFiltro);
		}
	}
}
