package br.com.servico.banco_dados.mysql.aterweb.dominio.pessoa;

public enum Situacao {

	A("Ativo", 1), F("Inativo por Falecimento", 3), O("Inativo por Outro Motivo", 4), P("Inativo por Pendências Cadastrais", 5), U("Inativo por Falta de Uso", 2);

	private String descricao;

	private Integer ordem;

	private Situacao(String descricao, Integer ordem) {
		this.descricao = descricao;
		this.ordem = ordem;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getOrdem() {
		return ordem;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}
