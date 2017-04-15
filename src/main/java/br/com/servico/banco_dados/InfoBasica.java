package br.com.servico.banco_dados;

public interface InfoBasica<E extends _ChavePrimaria<?>> {

	enum Nivel {
		ESPECIFICA, PADRAO, SO_ID;
	}

	default E infoBasica() {
		return infoBasica(Nivel.PADRAO);
	}

	E infoBasica(Nivel nivel);

}