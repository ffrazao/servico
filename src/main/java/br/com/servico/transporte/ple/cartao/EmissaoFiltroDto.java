package br.com.servico.transporte.ple.cartao;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.servico.entrada.json.JsonDeserializerData;
import br.com.servico.entrada.json.JsonSerializerData;
import br.com.servico.transporte._FiltroDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmissaoFiltroDto extends _FiltroDtoImpl {

	private static final long serialVersionUID = 1L;

	@NotNull
	@JsonSerialize(using = JsonSerializerData.class)
	@JsonDeserialize(using = JsonDeserializerData.class)
	private Calendar inicio;

	@NotNull
	@JsonSerialize(using = JsonSerializerData.class)
	@JsonDeserialize(using = JsonDeserializerData.class)
	private Calendar termino;

}
