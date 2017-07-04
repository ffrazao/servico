package br.com.servico.entrada.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.servico.negocio.FacadeBo;

public abstract class _BaseRest {
	
	public static final String FORMATO_DADOS_PADRAO = "application/json; charset=UTF-8";

	@Autowired
	private FacadeBo facadeBo;

	protected ObjectMapper criarObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		result.setSerializationInclusion(Include.NON_NULL);
		result.setSerializationInclusion(Include.NON_EMPTY);
		result.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return result;
	}

	public FacadeBo getFacadeBo() {
		return facadeBo;
	}

}