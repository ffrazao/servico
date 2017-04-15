package br.com.servico.negocio.assunto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.servico.banco_dados.mysql.aterweb.dao.PessoaDao;
import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;
import br.com.servico.negocio._BoModelo._ExcluirCmd;

@Service("PessoaExcluirCmd")
@Scope("prototype")
public class ExcluirCmd extends _ExcluirCmd<Pessoa, Integer> {

	@Autowired
	public ExcluirCmd(PessoaDao dao) {
		super(dao);
	}

}