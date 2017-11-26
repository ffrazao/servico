package br.com.servico.negocio.aterweb.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;
import br.com.frazao.cadeiaresponsabilidade.Comando;

@Service("AterwebPessoaFiltrarCh")
@Scope("prototype")
public class FiltrarCh extends CadeiaSequenciada {

	@Autowired
	public FiltrarCh(@Qualifier("AterwebPessoaFiltrarCmd") Comando c1) {
		adicionarComando(c1);
	}

}
