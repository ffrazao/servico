package br.com.servico.negocio.assunto;
/*
import org.apache.commons.chain.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.servico.banco_dados.mysql.aterweb.dao.PessoaDao;
import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;
import br.com.servico.negocio._BoModelo._SalvarCmd;

@Service("PessoaSalvarCmd")
@Scope("prototype")
public class SalvarCmd extends _SalvarCmd<Pessoa, Integer> {

	@Autowired
	public SalvarCmd(PessoaDao dao) {
		super(dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean execute(Context context) throws Exception {
		Pessoa requisicao = (Pessoa) context.get("requisicao");
		Pessoa salvo = (Pessoa) context.get("salvo");

		getDao().save(requisicao);

		if (salvo == null) {
			// gerar uma nova senha
			getDao().save(requisicao);
		}

		context.put("resposta", requisicao.getId());
		return false;
	}

}
*/