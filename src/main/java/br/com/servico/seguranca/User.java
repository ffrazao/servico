package br.com.servico.seguranca;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	// private Collection<? extends GrantedAuthority> authorities =
	// Arrays.asList(new SimpleGrantedAuthority[] { new
	// SimpleGrantedAuthority("USER") });
	private Collection<? extends GrantedAuthority> authorities;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;

	private Long id;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String newPassword;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long salt;

	private Calendar sessaoExpira;

	private String username;

	public User() {
	}

	public User(Long salt, String username, String password) {
		this.setId(salt);
		this.setSalt(salt);
		this.setUsername(username);
		this.setPassword(password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getNewPassword() {
		return newPassword;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public Long getSalt() {
		return salt;
	}

	public Calendar getSessaoExpira() {
		return sessaoExpira;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(Long salt) {
		this.salt = salt;
	}

	public void setSessaoExpira(Calendar sessaoExpira) {
		this.sessaoExpira = sessaoExpira;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
