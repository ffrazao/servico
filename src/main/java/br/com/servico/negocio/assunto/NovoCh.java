package br.com.servico.negocio.assunto;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PessoaNovoCh")
@Scope("prototype")
public class NovoCh extends ChainBase {
	
	@Autowired
	public NovoCh(NovoCmd c1) {
		addCommand(c1);
	}

}
