package br.com.servico.banco_dados.mysql.aterweb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.servico.banco_dados.DaoException;
import br.com.servico.transporte.aterweb.pessoa.PessoaFiltroDto;
import br.com.servico.transporte.aterweb.pessoa.PessoaListaDto;

public class PessoaDaoImpl implements PessoaDaoCustom {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<PessoaListaDto> filtrar(PessoaFiltroDto filtro) throws DaoException {
		List<PessoaListaDto> result = null;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql;
		sql = new StringBuilder();
		sql.append("select id, nome, situacao from pessoa.pessoa").append("\n");

		Query query = em.createNativeQuery(sql.toString(), PessoaListaDto.class);

		// inserir os parametros
		for (int i = 1; i <= params.size(); i++) {
			query.setParameter(i, params.get(i - 1));
		}

		// definir a pagina a ser consultada
		filtro.configuraPaginacao(query);

		// executar a consulta
		result = query.getResultList();

		// retornar
		return result;
	}

}
