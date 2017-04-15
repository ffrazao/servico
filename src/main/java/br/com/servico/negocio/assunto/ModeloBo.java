package br.com.servico.negocio.assunto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.servico.banco_dados.DaoException;
import br.com.servico.banco_dados.mysql.aterweb.dao.PessoaDao;
import br.com.servico.transporte.assunto.ModeloFitroDto;

@Service
public class ModeloBo {

	// @Autowired
	// private VwProdTotalPorStatusDao vwProdTotalPorStatusDao;
	//
	// @Autowired
	// private VwProdTotalNovosProtDao vwProdTotalNovosProtDao;
	//
	// @Autowired
	// private VwProdTotalTurnoOperadorDao vwProdTotalTurnoOperadorDao;
	//
	// @Autowired
	// private com.example.jpa.scie.dao.AgeBloQtdMaxEmbarqAgeDao
	// ageBloQtdMaxEmbarqAgeDao;
	//
	// @Autowired
	// @Qualifier("AgeBloQtdMaxEmbarqAgeDao2")
	// private com.example.jpa.scie2.dao.AgeBloQtdMaxEmbarqAgeDao
	// ageBloQtdMaxEmbarqAgeDao2;

	@Autowired
	private PessoaDao pessoaDao;

	@Transactional(readOnly = true)
	public Map<String, Object> relatorio() throws DaoException {
		Map<String, Object> result = new HashMap<>();
		result.put("pessoaList", pessoaDao.filtrar(new ModeloFitroDto()));

		return result;
	}

}
