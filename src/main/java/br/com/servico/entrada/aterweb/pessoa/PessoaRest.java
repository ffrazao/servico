package br.com.servico.entrada.aterweb.pessoa;

import java.security.Principal;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public ResponseEntity<Collection<PessoaListaDto>> filtrar(@RequestBody(required = true) PessoaFiltroDto filtro, Principal usuario, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getNestedPath());
		}
		return new ResponseEntity<Collection<PessoaListaDto>>((Collection<PessoaListaDto>) getFacadeBo().executarSomenteLeitura("AterwebPessoaFiltrarCh", filtro, usuario), HttpStatus.OK);
	}

}