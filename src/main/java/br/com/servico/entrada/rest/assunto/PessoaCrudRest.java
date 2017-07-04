package br.com.servico.entrada.rest.assunto;
/*
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import br.com.servico.entrada._BaseCrudRest;
import br.com.servico.transporte.assunto.ModeloFiltroDto;

@RestController
@RequestMapping("/pessoa")
public class PessoaCrudRest extends _BaseCrudRest<Pessoa, ModeloFiltroDto> {

	public PessoaCrudRest() {
		super("Pessoa", Pessoa.class, ModeloFiltroDto.class);
	}


	@RequestMapping("/mens")
	public SseEmitter getRealTimeMessageAction(HttpServletRequest request) throws IOException {
		// You can send message here
		sseEmitter.send("Message #1");
		return sseEmitter;
	}

	private SseEmitter sseEmitter = new SseEmitter();

	@RequestMapping("/ola")
	public SseEmitter ola() throws IOException {
		sseEmitter.send("Ola!");
		return sseEmitter;
	}

}
*/