package br.com.servico.banco_dados.oracle.ple.dao.cartao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.servico.banco_dados.DaoException;
import br.com.servico.transporte.ple.cartao.EmissaoFiltroDto;
import br.com.servico.transporte.ple.cartao.EmissaoListaDto;

@Repository("EmissaoDaoCustom")
public class EmissaoDaoImpl implements EmissaoDaoCustom {

	@Autowired
	private EntityManagerFactory emf;

	@SuppressWarnings("unchecked")
	@Override
	public List<EmissaoListaDto> filtrar(EmissaoFiltroDto filtro) throws DaoException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.RETORNOALUNO").append("\n");
		sql.append("     , b.nome ").append("\n");
		sql.append("     , a.RETORNODUPLICIDADE").append("\n");
		sql.append("FROM   ca_dados_transferencia a ").append("\n");
		sql.append("JOIN   CA_PESSOA_FISICA_PROTOCOLO b ").append("\n");
		sql.append("ON     b.id = a.PESSOAFISICAPROTOCOLO_ID").append("\n");
		sql.append("WHERE  a.DTTRANSFERENCIA >= :inicio").append("\n");
		sql.append("AND    a.DTTRANSFERENCIA <  :termino").append("\n");

		EntityManager em = emf.createEntityManager();

		Query query = em.createNativeQuery(sql.toString());

		query.setParameter("inicio", filtro.getInicio());
		query.setParameter("termino", filtro.getTermino());

		List<Object[]> temp = query.getResultList();

		if (temp == null) {
			return null;
		} else {
			List<EmissaoListaDto> result = new ArrayList<>();
			final ObjectMapper om = new ObjectMapper();
			temp.stream().forEach((r) -> {
				Object[] json = null;
				try {
					json = om.readValue((String) r[2], Object[].class);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				result.add(new EmissaoListaDto((String) r[0], (String) r[1], json));
			});
			return result;
		}
	}

}
