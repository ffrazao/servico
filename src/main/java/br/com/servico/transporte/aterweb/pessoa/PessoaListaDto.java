package br.com.servico.transporte.aterweb.pessoa;

import br.com.servico.banco_dados.mysql.aterweb.dominio.pessoa.Situacao;
import br.com.servico.transporte._ListaDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PessoaListaDto extends _ListaDtoImpl {

	private static final long serialVersionUID = 1L;

	private String id;

	private String nome;

	private Situacao situacao;

	public PessoaListaDto() {
	}

	public PessoaListaDto(String id, String nome, Situacao situacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.situacao = situacao;
	}

}
