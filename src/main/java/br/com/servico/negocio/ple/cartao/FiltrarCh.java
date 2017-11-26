package br.com.servico.negocio.ple.cartao;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("PleCartaoEmissaoFiltrarCh")
@Scope("prototype")
public class FiltrarCh extends CadeiaSequenciada {

	@Autowired
	public FiltrarCh(@Qualifier("PleCartaoEmissaoFiltrarCmd") Comando c1) {
		adicionarComando(c1);
	}

}
