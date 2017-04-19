package br.com.servico.seguranca.filtro;
/*
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.Transactional;

import br.com.servico.seguranca.TokenAuthenticationService;
import br.com.servico.seguranca.UserAuthentication;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final String DATA = "DATA";

	private static final String NUMERO_IP = "NUMERO_IP";

	private static final String ORIGIN = "ORIGIN";

	private static final String REFERER = "REFERER";

	private static final String USER_AGENT = "USER_AGENT";

	private final TokenAuthenticationService tokenAuthenticationService;

	public LoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping, "POST"));
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	@Transactional(readOnly = true)
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//		String dadosUsuario = null;//IOUtils.toString(request.getInputStream());
//		final Pessoa candidato = new ObjectMapper().readValue(dadosUsuario, Pessoa.class);
//		UserDetails user = null; //candidato.userDetails(); 
//		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//		Map<String, Object> details = new HashMap<String, Object>();
//		details.put(DATA, Calendar.getInstance());
//		details.put(NUMERO_IP, request.getRemoteAddr());
//		details.put(USER_AGENT, request.getHeader("user-agent"));
//		details.put(ORIGIN, request.getHeader("origin"));
//		details.put(REFERER, request.getHeader("referer"));
//		loginToken.setDetails(details);
//		Authentication result = getAuthenticationManager().authenticate(loginToken);
//		return result;
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		User usuario = (User) authentication.getPrincipal();

		// Lookup the complete User object from the database and create an
		// Authentication for it
		final UserAuthentication userAuthentication = null; //new UserAuthentication(usuario);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}
}
*/