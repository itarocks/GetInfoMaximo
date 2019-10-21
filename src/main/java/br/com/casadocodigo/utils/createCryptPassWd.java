package br.com.casadocodigo.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class createCryptPassWd {
	
	public static void main(String... args) {
		
		CharSequence teste = "123456";
		
		BCryptPasswordEncoder passWordEncoder = new BCryptPasswordEncoder();
		String passWdEncrypt = passWordEncoder.encode(teste);
		System.out.println("O valor da senha é " + passWdEncrypt);
		
	}

}
