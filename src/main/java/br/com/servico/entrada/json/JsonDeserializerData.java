package br.com.servico.entrada.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.ferramenta.UtilitarioData;

public class JsonDeserializerData extends JsonDeserializer<Calendar> {

	public JsonDeserializerData() {
	}

	@Override
	public Calendar deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Calendar result = null;
		try {
			result = UtilitarioData.getInstance().formataData(jp.getText());
		} catch (ParseException e) {
			try {
				String data = jp.getText();
				if (data.length() > 23) {
					data = data.substring(0, 23);
				}
				result = UtilitarioData.getInstance().formataDataJavascript(data);
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
		}
		return result;
	}
}
