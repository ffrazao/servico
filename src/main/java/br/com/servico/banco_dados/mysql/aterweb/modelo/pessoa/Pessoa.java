package br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.servico.banco_dados.EntidadeBase;
import br.com.servico.banco_dados.InfoBasica;
import br.com.servico.banco_dados._ChavePrimaria;
import br.com.servico.banco_dados.mysql.aterweb.dominio.pessoa.Situacao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "pessoa", schema = "pessoa")
@Data
@EqualsAndHashCode(callSuper = false)
public class Pessoa extends EntidadeBase implements _ChavePrimaria<Integer>, InfoBasica<Pessoa> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(updatable = false)
	private Situacao situacao;

	public Pessoa() {
	}
	
	public Pessoa(Integer id) {
		super(id);
	}

	public Pessoa(Integer id, String nome) {
		setId(id);
		setNome(nome);
	}

	public Pessoa(Integer id, String nome, Situacao situacao2) {
		this(id, nome);
		setSituacao(situacao);
	}

	@Override
	public Pessoa infoBasica(Nivel nivel) {
		switch (nivel) {
		case SO_ID:
			return new Pessoa(this.id);
		case ESPECIFICA:
			return new Pessoa(this.id, this.nome, this.situacao);
		default:
		case PADRAO:
			return new Pessoa(this.id, this.nome);
		}
	}

}
