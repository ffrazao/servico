package br.com.servico.transporte.assunto;

import javax.persistence.Entity;

import br.com.servico.banco_dados.mysql.aterweb.dominio.pessoa.Situacao;
import br.com.servico.transporte._ListaDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class ModeloListaDto extends _ListaDtoImpl {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String nome;

	private Situacao situacao;

}
