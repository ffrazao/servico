package br.com.servico.transporte.ple.cartao;

import br.com.servico.transporte._ListaDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmissaoListaDto extends _ListaDtoImpl {

	private static final long serialVersionUID = 1L;

	private String codigoTdmax;

	private String nomeSolicitante;

	private Object[] retorno;

	public EmissaoListaDto() {
	}

	public EmissaoListaDto(String codigoTdmax, String nomeSolicitante, Object[] retorno) {
		super();
		this.codigoTdmax = codigoTdmax;
		this.nomeSolicitante = nomeSolicitante;
		this.retorno = retorno;
	}

}
