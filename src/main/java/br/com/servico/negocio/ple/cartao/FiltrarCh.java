package br.com.servico.negocio.ple.cartao;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PleCartaoEmissaoFiltrarCh")
@Scope("prototype")
public class FiltrarCh extends ChainBase {
	
	@Autowired
	public FiltrarCh(FiltrarCmd c1) {
		addCommand(c1);
	}

}
