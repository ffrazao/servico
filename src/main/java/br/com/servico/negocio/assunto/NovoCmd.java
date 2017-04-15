package br.com.servico.negocio.assunto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.servico.banco_dados.mysql.aterweb.dao.PessoaDao;
import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;
import br.com.servico.negocio._BoModelo._NovoCmd;

@Service("PessoaNovoCmd")
@Scope("prototype")
public class NovoCmd extends _NovoCmd<Pessoa, Integer> {

	@Autowired
	public NovoCmd(PessoaDao dao) {
		super(dao, Pessoa.class);
	}

}
