package br.com.servico.dominio;

import br.com.servico.transporte._DominioDto;

public enum Confirmacao implements _DominioDto {

	N("Não", 1), S("Sim", 2);

	private String descricao;

	private Integer ordem;

	private Confirmacao(String descricao, Integer ordem) {
		this.descricao = descricao;
		this.ordem = ordem;
	}

	public Integer getOrdem() {
		return ordem;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}