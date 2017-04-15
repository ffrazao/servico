package br.com.servico.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.servico.seguranca.filtro.AutenticacaoFilter;
import br.com.servico.seguranca.filtro.LoginFilter;

@EnableWebSecurity
@Configuration
@Order(1)
public class _SegurancaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoProvider customAuthenticationProvider;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public _SegurancaConfig() {
		super(true);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().and()
				.servletApi().and()
				.anonymous().and()
				.authorizeRequests()

				// allow anonymous resource requests
				.antMatchers("/resources/**").permitAll()

				// allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/criar-conta/**").permitAll()
				.antMatchers("/api/senha-esqueci/**").permitAll()
				.antMatchers("/api/users/current").permitAll()
				
				.antMatchers("/").permitAll()
				.antMatchers("/*").permitAll()
				.antMatchers("/app/**").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/bin/**").permitAll()
				.antMatchers("/download/**").permitAll()
				.antMatchers("/obj/**").permitAll()
				.antMatchers("/Properties/**").permitAll()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/servidor.url").permitAll()

				.antMatchers(HttpMethod.POST, "/candidato/incluir-padrao").permitAll()
				.antMatchers("/dominio").permitAll()
				.antMatchers("/dominio/**").permitAll()

				.antMatchers("/candidato/cpf-valido/**").permitAll()
				.antMatchers("/candidato/cpf-ja-cadastrado/**").permitAll()

				// defined Admin only API area
				.antMatchers("/admin/**").hasRole("ADMIN")

				// all other request need to be authenticated
				.anyRequest().fullyAuthenticated().and()
				//.anyRequest().permitAll().and()

				// custom JSON based authentication by POST of
				// {"username":"<name>","password":"<password>"} which sets the
				// token header upon authentication
				.addFilterBefore(new LoginFilter("/api/login", tokenAuthenticationService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

				// custom Token based authentication based on the header
				// previously given to the client
				.addFilterBefore(new AutenticacaoFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}

}