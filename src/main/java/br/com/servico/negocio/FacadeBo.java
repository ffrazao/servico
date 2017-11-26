package br.com.servico.negocio;

import java.security.Principal;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.com.frazao.cadeiaresponsabilidade.ContextoBase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacadeBo implements BeanFactoryAware {

	private BeanFactory beanFactory;

	private Object executar(String comandoNome, Object requisicao, Principal usuario, Contexto<String, Object> contexto) throws Exception {
		Comando comando = (Comando) this.beanFactory.getBean(comandoNome);
		if (comando == null) {
			throw new BoException("Comando não reconhecido pelo sistema [%s]", comandoNome);
		}
		if (contexto == null) {
			contexto = new ContextoBase<>();
		}
		contexto.put("comando", comando.getClass().getName());
		contexto.setRequisicao(requisicao);
		contexto.put("usuario", usuario);
		
		comando.executar(contexto);
		
		return contexto.getResposta();
	}

	@Transactional
	public Object executarComEscrita(String comando, Object requisicao) throws Exception {
		return executar(comando, requisicao, null, null);
	}

	@Transactional
	public Object executarComEscrita(String comando, Object requisicao, Principal usuario) throws Exception {
		return executar(comando, requisicao, usuario, null);
	}

	@Transactional
	public Object executarComEscrita(String comando, Object requisicao, Principal usuario, Contexto<String, Object> contexto) throws Exception {
		return executar(comando, requisicao, usuario, contexto);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(String comando, Object requisicao) throws Exception {
		return executar(comando, requisicao, null, null);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(String comando, Object requisicao, Principal usuario) throws Exception {
		return executar(comando, requisicao, usuario, null);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(String comando, Object requisicao, Principal usuario, Contexto<String, Object> contexto) throws Exception {
		return executar(comando, requisicao, usuario, contexto);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(String comando, Object requisicao) throws Exception {
		return executar(comando, requisicao, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(String comando, Object requisicao, Principal usuario) throws Exception {
		return executar(comando, requisicao, usuario, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(String comando, Object requisicao, Principal usuario, Contexto<String, Object> contexto) throws Exception {
		return executar(comando, requisicao, usuario, contexto);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
