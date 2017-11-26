package br.com.servico.negocio;

import static br.com.servico.banco_dados.UtilitarioInfoBasica.get;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.com.servico.banco_dados.EntidadeBase;
import br.com.servico.banco_dados.InfoBasica;
import br.com.servico.banco_dados._ChavePrimaria;
import br.com.servico.transporte._FiltroDto;

@SuppressWarnings("unchecked")
public abstract class _BoModelo<T extends EntidadeBase, ID extends Serializable> extends Comando {

	public abstract static class _ExcluirCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		public _ExcluirCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@SuppressWarnings("rawtypes")
		public void procedimento(Contexto contexto) throws Exception {
			ID[] requisicao = (ID[]) contexto.getRequisicao();
			if (requisicao != null) {
				for (ID id : requisicao) {
					getDao().delete(id);
				}
			}
		}
	}

	public static class _FiltrarCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		public _FiltrarCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@Override
		@SuppressWarnings("rawtypes")
		public void procedimento(Contexto contexto) throws Exception {
			Collection<T> result = getDao().findAll();
			if (result != null && result.size() > 0 && ((List<T>) result).get(0) instanceof InfoBasica) {
				List<InfoBasica> ibList = new ArrayList<>();
				for (T r : result) {
					ibList.add((InfoBasica) r);
				}
				contexto.setResposta(ibList);
			} else {
				contexto.setResposta(result);
			}
		}
	}

	public static class _FiltroNovoCmd<T extends EntidadeBase, ID extends Serializable, F extends _FiltroDto>
			extends _BoModelo<T, ID> {

		private Class<F> filtroClass;

		public _FiltroNovoCmd(JpaRepository<T, ID> dao, Class<F> filtroClass) {
			super(dao);
			this.filtroClass = filtroClass;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void procedimento(Contexto contexto) throws Exception {
			F filtro = (F) contexto.getRequisicao();
			if (filtro == null) {
				filtro = filtroClass.newInstance();
			}
			contexto.setResposta(filtro);
		}

	}

	public static class _NovoCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		private Class<T> entidadeClass;

		public _NovoCmd(JpaRepository<T, ID> dao, Class<T> entidadeClass) {
			super(dao);
			this.entidadeClass = entidadeClass;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void procedimento(Contexto contexto) throws Exception {
			T result = (T) contexto.getRequisicao();
			if (result == null) {
				result = entidadeClass.newInstance();
			}
			contexto.setResposta(result);
		}

	}

	public static class _RecuperarSalvoCmd<T extends EntidadeBase & _ChavePrimaria<ID>, ID extends Serializable>
			extends _BoModelo<T, ID> {

		public _RecuperarSalvoCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void procedimento(Contexto contexto) throws Exception {
			T requisicao = (T) contexto.getRequisicao();
			// T result = ((Optional<T>) getDao().findOne((ID)
			// requisicao.getId())).get();
			T result = (T) getDao().findOne((ID) requisicao.getId());

			contexto.put("salvo", result);
		}

	}

	public static class _SalvarCmd<T extends EntidadeBase & _ChavePrimaria<ID>, ID extends Serializable>
			extends _BoModelo<T, ID> {

		public _SalvarCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@Override
		public void procedimento(Contexto<?, ?> contexto) throws Exception {
			T requisicao = (T) contexto.getRequisicao();
			getDao().save(requisicao);
			contexto.setResposta(requisicao.getId());
		}

	}

	public static class _VisualizarCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		public _VisualizarCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void procedimento(Contexto<?, ?> contexto) throws Exception {
			ID[] requisicao = (ID[]) contexto.getRequisicao();
			Collection<T> result = new ArrayList<>();
			for (ID id : requisicao) {
				// T item = ((Optional<T>) getDao().findOne(id)).get();
				T item = (T) getDao().findOne(id);
				result.add(item instanceof InfoBasica ? (T) get((InfoBasica) item) : item);
			}
			contexto.setResposta(result);
		}

	}

	private JpaRepository<T, ID> dao;

	public _BoModelo() {
	}

	public _BoModelo(JpaRepository<T, ID> dao) {
		this.dao = dao;
	}

	public JpaRepository<T, ID> getDao() {
		return dao;
	}

}