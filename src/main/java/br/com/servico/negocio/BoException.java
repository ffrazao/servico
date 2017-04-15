package br.com.servico.negocio;

public class BoException extends Exception {

	private static final long serialVersionUID = 1L;

	public BoException() {
		super();
	}

	public BoException(String message) {
		super(message);
	}

	public BoException(String mensagem, Object ... itens) {
		this(String.format(mensagem, (Object[]) itens));
	}

	public BoException(String message, Throwable cause) {
		super(message, cause);
	}

	public BoException(String mensagem, Throwable cause, Object ... itens) {
		this(String.format(mensagem, (Object[]) itens), cause);
	}
	public BoException(Throwable cause) {
		super(cause);
	}
}