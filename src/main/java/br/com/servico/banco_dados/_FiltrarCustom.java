package br.com.servico.banco_dados;

import java.util.List;

import br.com.servico.transporte._Dto;

public interface _FiltrarCustom<F extends _Dto> {

	List<? extends _Dto> filtrar(F filtro) throws DaoException;

}
