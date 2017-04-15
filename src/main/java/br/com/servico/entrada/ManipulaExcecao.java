package br.com.servico.entrada;

import javax.validation.ConstraintViolationException;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ferramenta.UtilitarioString;

@ControllerAdvice
public class ManipulaExcecao {

	// @Autowired
	// private MessageSource messageSource;

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public VndErrors handleAllException(Exception ex) {
		ex.printStackTrace();
		if (ex instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException) ex;
			return new VndErrors("error", UtilitarioString.collectionToString(cve.getConstraintViolations()));
		} else {
			return new VndErrors("error", ex.getMessage());
		}
	}

}