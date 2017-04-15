package br.com.servico.transporte;

import lombok.Data;

@Data
public class _TagDto implements _Dto {

	private static final long serialVersionUID = 1L;

	private String conteudo;

	public _TagDto() {
	}

	public _TagDto(String conteudo) {
		this.conteudo = conteudo;
	}

}
