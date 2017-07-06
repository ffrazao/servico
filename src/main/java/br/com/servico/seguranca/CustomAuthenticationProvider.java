package br.com.servico.seguranca;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String name = authentication.getName();
			String password = authentication.getCredentials().toString();

			if (name != null) {
				return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
			}
			return null;
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
