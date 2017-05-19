package br.com.servico.entrada.aterweb.pessoa;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.entrada._BaseRest;
import br.com.servico.transporte.aterweb.pessoa.PessoaFiltroDto;
import br.com.servico.transporte.aterweb.pessoa.PessoaListaDto;

@RestController
@RequestMapping("/pessoa")
public class PessoaRest extends _BaseRest {

	public PessoaRest() {
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, path = "/teste")
	public ResponseEntity<Collection<PessoaListaDto>> filtrar(@Valid @RequestBody(required = true) PessoaFiltroDto filtro, Errors errors) throws Exception {
		if (errors.hasErrors()) {
			throw new Exception(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<Collection<PessoaListaDto>>((Collection<PessoaListaDto>) getFacadeBo().executarSomenteLeitura("AterwebPessoaFiltrarCh", filtro, null), HttpStatus.OK);
	}

}