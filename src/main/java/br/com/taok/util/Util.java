package br.com.taok.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Util {

	public static Date asDate(LocalDate localDate) {
		
		return Date.from( localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant() );
	}
}
