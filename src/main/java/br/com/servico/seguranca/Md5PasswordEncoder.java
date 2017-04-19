package br.com.servico.seguranca;
/*
import static br.com.ferramenta.Criptografia.MD5;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.encoding.PasswordEncoder;

@SuppressWarnings("deprecation")
public class Md5PasswordEncoder implements PasswordEncoder {

	@Override
	public String encodePassword(String rawPassword, Object salt) {
		try {
			return MD5((salt == null ? "" : salt).toString().concat(rawPassword));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPassword, Object salt) {
		return encodePassword(rawPassword, salt).equalsIgnoreCase(encPass);
	}
}
*/