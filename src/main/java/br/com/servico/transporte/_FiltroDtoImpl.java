package br.com.servico.transporte;

import javax.persistence.Query;

import lombok.Data;

@Data
public abstract class _FiltroDtoImpl implements _FiltroDto {

	private static final long serialVersionUID = 1L;

	private Integer numeroPagina;

	private Integer registrosPagina;

	private TemMaisRegistros temMaisRegistros;

	public void configuraPaginacao(Query query) {
		Integer ini = this.getNumeroPagina() == null ? 1 : this.getNumeroPagina();
		Integer tam = _FiltroDto.TAMANHO_PAGINA;

		ini = (ini - 1) * tam;

		if (this.getTemMaisRegistros() != null) {
			switch (this.getTemMaisRegistros()) {
			case PRIMEIRA_PAGINA:
				ini = 1;
				break;
			case ULTIMA_PAGINA:
				tam = null;
			case PROXIMA_PAGINA:
				if (ini == 0) {
					ini = tam;
				}
			default:
				break;
			}
		}
		query.setFirstResult(ini);
		if (tam != null) {
			query.setMaxResults(tam);
		}
	}

}