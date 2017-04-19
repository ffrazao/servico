package br.com.servico.transporte.assunto;

import br.com.servico.transporte._FiltroDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ModeloFiltroDto extends _FiltroDtoImpl {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String nome;

	//private Situacao situacao;

}
