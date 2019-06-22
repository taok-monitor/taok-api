package br.com.taok.post;

import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;

public class CriadorDePost {

	public static void postar(String mensagem) {

		String token = "EAAGFY4qlkKkBACj7fs7okzlHemF9pctWO5ewP1XT5ksLaFNs5tsNinDQgcNJJ9a7beb3jhZAufZA4RqZAqi8UoJZAIWYwD20CAyq4ETcfgBvzNDiASaqcavoyjdDS3IZAr8nzE4B56nwad5PZAgOwe0m7Jq4lnif9caXVKwCgUET7cFIE94ZBtFsOZAikVk7iZByGcegpclDKwwZDZD";
		
		try {

			new DefaultFacebookClient(token,Version.VERSION_3_3)
				.publish("me/feed", FacebookType.class, Parameter.with("message", mensagem));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
