package br.com.servico.entrada.json;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.ferramenta.UtilitarioNumero;

public class JsonFormatarBigDecimal extends JsonDeserializer<BigDecimal> {

	public JsonFormatarBigDecimal() {
	}

	@Override
	public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		BigDecimal result = UtilitarioNumero.getInstance().stringToBigDecimal(jp.getText());
		return result;
	}
}