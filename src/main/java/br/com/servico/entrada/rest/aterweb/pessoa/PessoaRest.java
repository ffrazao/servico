package br.com.servico.entrada.rest.aterweb.pessoa;

import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.entrada.rest._BaseRest;
import br.com.servico.transporte.aterweb.pessoa.PessoaFiltroDto;
import br.com.servico.transporte.aterweb.pessoa.PessoaListaDto;

@RestController
@RequestMapping(path = "/pessoa")//, consumes = _BaseRest.FORMATO_DADOS_PADRAO, produces = _BaseRest.FORMATO_DADOS_PADRAO)
public class PessoaRest extends _BaseRest {

	@Autowired
	private MessageSource ms;

	public PessoaRest() {
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, path = "/teste")
	private ResponseEntity<Collection<PessoaListaDto>> filtrar(@Valid @RequestBody(required = true) PessoaFiltroDto filtro, Errors errors, Locale locale) throws Exception {
		System.out.println(ms.getMessage("erro.nao_nulo", new Object[] { "Frz" }, locale));
		if (errors.hasErrors()) {
			// throw new Exception(errors.getAllErrors().stream().map(x ->
			// x.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		return new ResponseEntity<Collection<PessoaListaDto>>((Collection<PessoaListaDto>) getFacadeBo().executarSomenteLeitura("AterwebPessoaFiltrarCh", filtro, null), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/teste")
	private ResponseEntity<String> teste() {
		return new ResponseEntity<>("Testado", HttpStatus.OK);
	}

}