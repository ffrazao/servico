package br.com.servico.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Classe customizada para fazer a autenticação de usuários
 * 
 * @author frazao
 * 
 */
@Component("authenticationProvider")
public class AutenticacaoProvider extends DaoAuthenticationProvider {

	public AutenticacaoProvider() {
		ReflectionSaltSource ss = new ReflectionSaltSource();
		ss.setUserPropertyToUse("salt");
		this.setSaltSource(ss);
		this.setPasswordEncoder(new Md5PasswordEncoder());
		// o codigo seguinte configura verificacoes antes de verificar se a
		// senha é valida ou nao
		// this.setPreAuthenticationChecks(preAuthenticationChecks);
		// o codigo seguiinte faz a verificacao das credenciais do usuario
		// this.additionalAuthenticationChecks(userDetails, authentication);
		// o codigo seguinte configura verificacoes depois de verificar se a
		// senha é valida ou nao
		// this.setPostAuthenticationChecks(postAuthenticationChecks);
	}

	@Override
	public Authentication authenticate(Authentication autenticacao) throws AuthenticationException {
		Authentication result;
		result = super.authenticate(autenticacao);
		return result;
	}

	@Autowired
	@Qualifier("userDetailsService")
	@Override
	public void setUserDetailsService(org.springframework.security.core.userdetails.UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}
}