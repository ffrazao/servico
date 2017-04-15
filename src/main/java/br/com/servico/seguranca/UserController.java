package br.com.servico.seguranca;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.servico.banco_dados.mysql.aterweb.dao.PessoaDao;
import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;

@RestController
public class UserController {

	@Autowired
	PessoaDao userRepository;

	@RequestMapping(value = "/api/users/current", method = RequestMethod.PATCH)
	public ResponseEntity<String> changePassword(@RequestBody final Pessoa user) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		final Pessoa currentUser = userRepository.findOneByNomeUsuario(authentication.getName());
//
//		if (user.getSenhaNova() == null || user.getSenhaNova().length() < 4) {
//			return new ResponseEntity<String>("new password to short", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//
//		final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
//		if (!pwEncoder.matches(user.getSenha(), currentUser.getSenha())) {
//			return new ResponseEntity<String>("old password mismatch", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//
//		currentUser.setSenha(pwEncoder.encode(user.getSenhaNova()));
//		userRepository.saveAndFlush(currentUser);
		return new ResponseEntity<String>("password changed", HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
	public String getCurrent(Principal p) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			return authentication.getName();
		}
		return authentication.getName(); // anonymous user support
	}

	@RequestMapping(value = "/admin/api/users/{user}/grant/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> grantRole(
			@PathVariable Pessoa user/* , @PathVariable UserRole role */) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// user.grantRole(role);
		userRepository.saveAndFlush(user);
		return new ResponseEntity<String>("role granted", HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/api/users", method = RequestMethod.GET)
	public List<Pessoa> list() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/admin/api/users/{user}/revoke/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> revokeRole(
			@PathVariable Pessoa user/* , @PathVariable UserRole role */) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// user.revokeRole(role);
		userRepository.saveAndFlush(user);
		return new ResponseEntity<String>("role revoked", HttpStatus.OK);
	}
}
