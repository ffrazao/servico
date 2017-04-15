package br.com.servico.negocio.assunto;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PessoaEditarCh")
@Scope("prototype")
public class EditarCh extends ChainBase {
	
	@Autowired
	public EditarCh(RecuperarSalvoCmd c1, SalvarCmd c2) {
		addCommand(c1);
		addCommand(c2);
	}

}
