package br.com.servico.entrada.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	// private SimpleModule simpleModule = new SimpleModule();

	public JsonHibernateAwareObjectMapper() {

		// habilitar o modulo de manipula��o das entidades Hibernate
		// module.configure(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS,
		// true);
		// module.configure(Feature.FORCE_LAZY_LOADING, true);

		// Hibernate4Module hibernate4Module = new Hibernate4Module();
		// registerModule(hibernate4Module);

		// para configurar o formato padr�o das datas
		// serializadas/desserializadas
		setDateFormat(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));

		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// remover todos os itens nulos ou vazios das serializacoes json
		setSerializationInclusion(Include.NON_NULL);
		setSerializationInclusion(Include.NON_EMPTY);

		// enable(SerializationFeature.INDENT_OUTPUT);

		// para habilitar a identifica��o de todas as classes enviadas e
		// recebidas pelo Json
		// enableDefaultTyping();
		// enableDefaultTyping(DefaultTyping.NON_FINAL);
		// enableDefaultTyping(DefaultTyping.NON_FINAL,
		// JsonTypeInfo.As.WRAPPER_OBJECT);

	}
}
