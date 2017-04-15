package br.com.servico.entrada.json;

import java.io.IOException;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.ferramenta.UtilitarioData;

public class JsonSerializerDataHora extends JsonSerializer<Calendar> {

	@Override
	public void serialize(Calendar date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
		if (date != null) {
			gen.writeString(UtilitarioData.getInstance().formataDataHora(date));
		}
	}
}