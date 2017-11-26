package br.com.servico.negocio.aterweb.pessoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.com.servico.banco_dados.mysql.aterweb.dao.pessoa.PessoaDao;
import br.com.servico.transporte.aterweb.pessoa.PessoaFiltroDto;
import br.com.servico.transporte.aterweb.pessoa.PessoaListaDto;

@Service("AterwebPessoaFiltrarCmd")
@Scope("prototype")
public class FiltrarCmd extends Comando {

	@Autowired
	private PessoaDao dao;

	int contador;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true)
	public void procedimento(Contexto context) throws Exception {
		// retorno bruto, ou seja, retorno dos dados do banco de dados filtrado
		// somente pelo perído informado
		List<PessoaListaDto> result = (List<PessoaListaDto>) dao.filtrar((PessoaFiltroDto) context.get("requisicao"));
		context.setResposta(result);
	}

}
