package br.com.servico.entrada;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.servico.transporte._FiltroDto;

@SuppressWarnings("unchecked")
public abstract class _BaseCrudRest<E, D extends _FiltroDto> extends _BaseRest {

	private final Class<D> cadFiltroDtoClass;

	private final Class<E> entidadeClass;

	protected final String nomeCrud;

	public _BaseCrudRest(String nomeCrud, Class<E> entidadeClass, Class<D> cadFiltroDtoClass) {
		this.nomeCrud = nomeCrud;
		this.entidadeClass = entidadeClass;
		this.cadFiltroDtoClass = cadFiltroDtoClass;
	}

	private D criarDto(String filtro) throws Exception {
		D result = null;
		if (filtro != null) {
			result = criarObjectMapper().readValue(filtro, cadFiltroDtoClass);
		}
		return result;
	}

	private E criarEntidade(String modelo) throws Exception {
		E result = null;
		if (modelo != null) {
			result = criarObjectMapper().readValue(modelo, entidadeClass);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Long> editar(@Valid @RequestBody E requisicao, Principal usuario) throws Exception {
		return new ResponseEntity<Long>((Long) getFacadeBo().executarComEscrita(String.format("%sEditarCh", this.nomeCrud), requisicao, usuario), HttpStatus.OK);
	}

	@RequestMapping(value = "/{requisicao}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Long[] requisicao, Principal usuario) throws Exception {
		getFacadeBo().executarComEscrita(String.format("%sExcluirCh", this.nomeCrud), requisicao, usuario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<E>> filtrar(@RequestParam(required = false) String filtro, Principal usuario) throws Exception {
		return new ResponseEntity<Collection<E>>((Collection<E>) getFacadeBo().executarSomenteLeitura(String.format("%sFiltrarCh", this.nomeCrud), criarDto(filtro), usuario), HttpStatus.OK);
	}

	@RequestMapping(value = { "/filtro", "/filtro/{requisicao}" }, method = RequestMethod.GET)
	public ResponseEntity<D> filtroNovo(@PathVariable(required = false) String requisicao, Principal usuario) throws Exception {
		return new ResponseEntity<D>((D) getFacadeBo().executarSomenteLeitura(String.format("%sFiltroNovoCh", this.nomeCrud), criarDto(requisicao), usuario), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Long> incluir(@Valid @RequestBody E requisicao, Principal usuario) throws Exception {
		return new ResponseEntity<Long>((Long) getFacadeBo().executarComEscrita(String.format("%sIncluirCh", this.nomeCrud), requisicao, usuario), HttpStatus.OK);
	}

	@RequestMapping(value = { "/novo", "/novo/{requisicao}" }, method = RequestMethod.GET)
	public ResponseEntity<E> novo(@PathVariable(required = false) String requisicao, Principal usuario) throws Exception {
		return new ResponseEntity<E>((E) getFacadeBo().executarSomenteLeitura(String.format("%sNovoCh", this.nomeCrud), criarEntidade(requisicao), usuario), HttpStatus.OK);
	}

	@RequestMapping(value = "/{requisicao}", method = RequestMethod.GET)
	public ResponseEntity<E> visualizar(@PathVariable Long[] requisicao, Principal usuario) throws Exception {
		return new ResponseEntity<E>((E) getFacadeBo().executarSomenteLeitura(String.format("%sVisualizarCh", this.nomeCrud), requisicao, usuario), HttpStatus.OK);
	}

}