package br.com.servico.negocio;

import static br.com.servico.banco_dados.UtilitarioInfoBasica.get;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.servico.banco_dados.EntidadeBase;
import br.com.servico.banco_dados.InfoBasica;
import br.com.servico.banco_dados._ChavePrimaria;
import br.com.servico.transporte._FiltroDto;

@SuppressWarnings("unchecked")
public abstract class _BoModelo<T extends EntidadeBase, ID extends Serializable> implements Command {

	public abstract static class _ExcluirCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		public _ExcluirCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		public boolean execute(Context context) throws Exception {
			ID[] requisicao = (ID[]) context.get("requisicao");
			if (requisicao != null) {
				for (ID id : requisicao) {
					getDao().delete(id);
				}
			}
			return false;
		}
	}

	public static class _FiltrarCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		public _FiltrarCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@Override
		@SuppressWarnings("rawtypes")
		public boolean execute(Context context) throws Exception {
			Collection<T> result = getDao().findAll();
			if (result != null && result.size() > 0 && ((List<T>) result).get(0) instanceof InfoBasica) {
				List<InfoBasica> ibList = new ArrayList<>();
				for (T r : result) {
					ibList.add((InfoBasica) r);
				}
				context.put("resposta", ibList);
			} else {
				context.put("resposta", result);
			}
			return false;
		}
	}

	public static class _FiltroNovoCmd<T extends EntidadeBase, ID extends Serializable, F extends _FiltroDto> extends _BoModelo<T, ID> {

		private Class<F> filtroClass;

		public _FiltroNovoCmd(JpaRepository<T, ID> dao, Class<F> filtroClass) {
			super(dao);
			this.filtroClass = filtroClass;
		}

		@Override
		public boolean execute(Context context) throws Exception {
			F filtro = (F) context.get("requisicao");
			if (filtro == null) {
				filtro = filtroClass.newInstance();
			}
			context.put("resposta", filtro);
			return false;
		}

	}

	public static class _NovoCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		private Class<T> entidadeClass;

		public _NovoCmd(JpaRepository<T, ID> dao, Class<T> entidadeClass) {
			super(dao);
			this.entidadeClass = entidadeClass;
		}

		@Override
		public boolean execute(Context context) throws Exception {
			T result = (T) context.get("requisicao");
			if (result == null) {
				result = entidadeClass.newInstance();
			}
			context.put("resposta", result);
			return false;
		}

	}

	public static class _RecuperarSalvoCmd<T extends EntidadeBase & _ChavePrimaria<ID>, ID extends Serializable> extends _BoModelo<T, ID> {

		public _RecuperarSalvoCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@Override
		public boolean execute(Context context) throws Exception {
			T requisicao = (T) context.get("requisicao");
			//T result = ((Optional<T>) getDao().findOne((ID) requisicao.getId())).get();
			T result = (T) getDao().findOne((ID) requisicao.getId());

			context.put("salvo", result);
			return false;
		}

	}

	public static class _SalvarCmd<T extends EntidadeBase & _ChavePrimaria<ID>, ID extends Serializable> extends _BoModelo<T, ID> {

		public _SalvarCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@Override
		public boolean execute(Context context) throws Exception {
			T requisicao = (T) context.get("requisicao");
			getDao().save(requisicao);
			context.put("resposta", requisicao.getId());
			return false;
		}

	}

	public static class _VisualizarCmd<T extends EntidadeBase, ID extends Serializable> extends _BoModelo<T, ID> {

		public _VisualizarCmd(JpaRepository<T, ID> dao) {
			super(dao);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public boolean execute(Context context) throws Exception {
			ID[] requisicao = (ID[]) context.get("requisicao");
			Collection<T> result = new ArrayList<>();
			for (ID id : requisicao) {
				//T item = ((Optional<T>) getDao().findOne(id)).get();
				T item = (T) getDao().findOne(id);
				result.add(item instanceof InfoBasica ? (T) get((InfoBasica) item) : item);
			}
			context.put("resposta", result);
			return false;
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