package br.com.servico.seguranca;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String login = auth.getName();
		String senha = auth.getCredentials().toString();

		// Defina suas regras para realizar a autenticação

//		if (usuarioBd != null) {
//			if (usuarioAtivo(usuarioBd)) {
//				Collection<? extends GrantedAuthority> authorities = usuarioBd.getPapeis();
//				return new UsernamePasswordAuthenticationToken(login, senha, authorities);
//			} else {
//				throw new BadCredentialsException("Usuário inativo!");
//			}
//		}

		throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
	}
	
//	private boolean usuarioAtivo(Usuario usuario) {
//        if (usuario != null) {
//            if (usuario.getStatus() == true) {
//                return true;
//            }
//        }
//        return false;
//    }

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
