package br.com.servico.entrada.assunto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;
import br.com.servico.entrada._BaseCrudRest;
import br.com.servico.transporte.assunto.ModeloFiltroDto;

@RestController
@RequestMapping("/pessoa")
public class PessoaCrudRest extends _BaseCrudRest<Pessoa, ModeloFiltroDto> {

	public PessoaCrudRest() {
		super("Pessoa", Pessoa.class, ModeloFiltroDto.class);
	}

}