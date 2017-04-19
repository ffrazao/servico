package br.com.servico.entrada.ple.cartao;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.entrada._BaseRest;
import br.com.servico.transporte.ple.cartao.EmissaoFiltroDto;
import br.com.servico.transporte.ple.cartao.EmissaoListaDto;

@RestController
@RequestMapping("/aluno")
public class EmissaoRest extends _BaseRest {

	public EmissaoRest() {
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, path = "/emissao-cartao")
	public ResponseEntity<Collection<EmissaoListaDto>> filtrar(@RequestBody(required = true) @Valid EmissaoFiltroDto filtro, Principal usuario) throws Exception {
		return new ResponseEntity<Collection<EmissaoListaDto>>((Collection<EmissaoListaDto>) getFacadeBo().executarSomenteLeitura("PleCartaoEmissaoFiltrarCh", filtro, usuario), HttpStatus.OK);
	}

}