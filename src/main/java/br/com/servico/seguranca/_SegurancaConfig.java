package br.com.servico.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class _SegurancaConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		/*http
			.authorizeRequests()
				.anyRequest()
					.permitAll().and()
					.csrf()
					.disable();*/
		http
			.authorizeRequests()
				.antMatchers("/login")
					.permitAll()
				.antMatchers("/oauth/token/revokeById/**")
					.permitAll()
				.antMatchers("/tokens/**")
					.permitAll()
				.antMatchers("/resources/**")
					.permitAll()
			.anyRequest()
				.authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("usuario")
				.passwordParameter("senha")
				.permitAll()
		.and()
            .logout()                                    
                .permitAll()
		.and()
			.csrf()
				.disable();
	}

	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("john").password("123").roles("USER")
			.and()
				.withUser("tom").password("111").roles("ADMIN")
			.and()
				.withUser("user1").password("pass").roles("USER")
			.and()
				.withUser("admin").password("nimda").roles("ADMIN");
	}

}