package br.com.servico.entrada.assunto;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.negocio.assunto.ModeloBo;

@RestController
public class ModeloRest {
	
	@Autowired
	private ModeloBo bo;
	
	@RequestMapping(value = "/relatorio")
	public ResponseEntity<Map<String, Object>> filtroExecutar(Principal usuario) throws Exception {
		return new ResponseEntity<>(bo.relatorio(), HttpStatus.OK);
	}

}
