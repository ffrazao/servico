package br.com.servico.negocio.assunto;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PessoaFiltroNovoCh")
@Scope("prototype")
public class FiltroNovoCh extends ChainBase {

	@Autowired
	public FiltroNovoCh(FiltroNovoCmd c1) {
		addCommand(c1);
	}

}
