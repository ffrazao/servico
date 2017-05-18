package br.com.servico.transporte.aterweb.pessoa;

import javax.validation.constraints.NotNull;

import br.com.servico.banco_dados.mysql.aterweb.dominio.pessoa.Situacao;
import br.com.servico.transporte._FiltroDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PessoaFiltroDto extends _FiltroDtoImpl {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String id;

	private String nome;

	private Situacao situacao;

}
