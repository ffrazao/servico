package br.com.servico.seguranca;
/*
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

	// private static final long _ONE_DAYS = 1000 * 60 * 60 * 24 * 1;

	// private static final long _ONE_HOUR = 1000 * 60 * 60 * 1;

	private static final long _TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	private static final long EXPIRES_TIME = _TEN_DAYS;

	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws IOException {
		final User user = authentication.getDetails();
		Calendar expiracao = new GregorianCalendar();
		expiracao.setTime(new Date(System.currentTimeMillis() + EXPIRES_TIME));
		// user.setExpiracaoSessao(expiracao);
		String token = tokenHandler.createTokenForUser(user);
		// StringBuffer buf = new StringBuffer();
		// for (int i = 0; i < 2; i++) {
		// buf.append("0");
		// }
		// response.addHeader(AUTH_HEADER_NAME, buf.toString());
		response.addHeader(AUTH_HEADER_NAME, token);
		response.addCookie(new Cookie(AUTH_HEADER_NAME, token));
		response.getWriter().print(String.format("{\"id\":%d,\"token\":\"%s\"}", user.getId(), token));
		response.getWriter().flush();
		response.flushBuffer();
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
*/