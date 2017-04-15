package br.com.servico.negocio.assunto;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PessoaIncluirCh")
@Scope("prototype")
public class IncluirCh extends ChainBase {
	
	@Autowired
	public IncluirCh(SalvarCmd c1) {
		addCommand(c1);
	}

}
