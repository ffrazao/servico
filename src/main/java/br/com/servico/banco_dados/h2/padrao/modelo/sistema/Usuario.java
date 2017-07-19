package br.com.servico.banco_dados.h2.padrao.modelo.sistema;

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
public class Usuario extends EntidadeBase implements _ChavePrimaria<Integer>, InfoBasica<Usuario> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(updatable = false)
	private Situacao situacao;

	public Usuario() {
	}
	
	public Usuario(Integer id) {
		super(id);
	}

	public Usuario(Integer id, String nome) {
		setId(id);
		setNome(nome);
	}

	public Usuario(Integer id, String nome, Situacao situacao2) {
		this(id, nome);
		setSituacao(situacao);
	}

	@Override
	public Usuario infoBasica(Nivel nivel) {
		switch (nivel) {
		case SO_ID:
			return new Usuario(this.id);
		case ESPECIFICA:
			return new Usuario(this.id, this.nome, this.situacao);
		default:
		case PADRAO:
			return new Usuario(this.id, this.nome);
		}
	}

}
