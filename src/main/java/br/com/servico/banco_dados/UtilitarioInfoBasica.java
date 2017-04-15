package br.com.servico.banco_dados;

import java.util.ArrayList;
import java.util.List;

public class UtilitarioInfoBasica {

	public static <T extends InfoBasica<? extends T>> List<T> infoBasicaList(final List<T> lista) {
		return infoBasicaList(lista, InfoBasica.Nivel.PADRAO);
	}

	public static <T extends InfoBasica<? extends T>> List<T> infoBasicaList(final List<T> lista, InfoBasica.Nivel nivel) {
		List<T> result = null;
		if (lista != null) {
			for (T registro : lista) {
				if (result == null) {
					result = new ArrayList<>();
				}
				result.add(registro == null ? null : (T) registro.infoBasica(nivel));
			}
		}
		return result;
	}

	public static <T extends InfoBasica<? extends T>> T infoBasicaReg(final T registro) {
		return infoBasicaReg(registro, InfoBasica.Nivel.PADRAO);
	}

	public static <T extends InfoBasica<? extends T>> T infoBasicaReg(final T registro, InfoBasica.Nivel nivel) {
		return registro == null ? null : (T) registro.infoBasica(nivel);
	}

}