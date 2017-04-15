package br.com.servico.banco_dados;

import java.util.Collection;

public class UtilitarioInfoBasica {

	public static <T extends InfoBasica<? extends T>> Collection<T> get(final Collection<T> colecao) {
		return get(colecao, InfoBasica.Nivel.PADRAO);
	}

	@SuppressWarnings("unchecked")
	public static <T extends InfoBasica<? extends T>> Collection<T> get(final Collection<T> colecao, InfoBasica.Nivel nivel) {
		Collection<T> result = null;
		if (colecao != null) {
			try {
				result = colecao.getClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			for (T registro : colecao) {
				result.add(get(registro, nivel));
			}
		}
		return result;
	}

	public static <T extends InfoBasica<? extends T>> T get(final T registro) {
		return get(registro, InfoBasica.Nivel.PADRAO);
	}

	public static <T extends InfoBasica<? extends T>> T get(final T registro, InfoBasica.Nivel nivel) {
		return registro == null ? null : (T) registro.infoBasica(nivel);
	}

}