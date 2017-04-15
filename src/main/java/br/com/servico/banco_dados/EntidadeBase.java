package br.com.servico.banco_dados;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Data;

// para marcar esta classe como o topo de hierarquia de entidades, porém não
// persiste informação
@MappedSuperclass
@Data
public abstract class EntidadeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public EntidadeBase() {
	}

	@SuppressWarnings("unchecked")
	public EntidadeBase(Serializable id) {
		if (this instanceof _ChavePrimaria) {
			((_ChavePrimaria<Serializable>) this).setId(id);
		} else {
			throw new IllegalArgumentException(String.format("A classe %s não implementa _ChavePrimaria<Serializable>", this.getClass().getName()));
		}
	}

}
