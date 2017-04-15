package br.com.servico.transporte;

public interface _FiltroDto extends _Dto {

	enum TemMaisRegistros {
		PRIMEIRA_PAGINA, PROXIMA_PAGINA, ULTIMA_PAGINA;
	}

	public static final String SEPARADOR_CAMPO = ";";

	int TAMANHO_PAGINA = 100;

	Integer getNumeroPagina();

	Integer getRegistrosPagina();

	TemMaisRegistros getTemMaisRegistros();

	void setNumeroPagina(Integer numeroPagina);

	void setRegistrosPagina(Integer registrosPagina);

	void setTemMaisRegistros(TemMaisRegistros temMaisRegistros);

}
