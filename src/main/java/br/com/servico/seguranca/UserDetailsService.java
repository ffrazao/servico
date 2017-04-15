package br.com.servico.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.servico.banco_dados.mysql.aterweb.dao.PessoaDao;
import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private PessoaDao dao;

	@Override
	@Transactional(readOnly = true)
	public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Pessoa usuario = dao.findOne(1).get();
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return null;//usuario.userDetails();
	}
}