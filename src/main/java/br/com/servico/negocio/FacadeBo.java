package br.com.servico.negocio;

import java.security.Principal;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacadeBo implements BeanFactoryAware {

	private BeanFactory beanFactory;

	@SuppressWarnings("unchecked")
	private Object executar(String comandoNome, Object requisicao, Principal usuario, Context context) throws Exception {
		Command comando = (Command) this.beanFactory.getBean(comandoNome);
		if (comando == null) {
			throw new BoException("Comando não reconhecido pelo sistema [%s]", comandoNome);
		}
		if (context == null) {
			context = new ContextBase();
		}
		context.put("comando", comando.getClass().getName());
		context.put("requisicao", requisicao);
		context.put("usuario", usuario);
		
		comando.execute(context);
		
		return context.get("resposta");
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
	public Object executarComEscrita(String comando, Object requisicao, Principal usuario, Context context) throws Exception {
		return executar(comando, requisicao, usuario, context);
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
	public Object executarComEscritaENovaTransacao(String comando, Object requisicao, Principal usuario, Context context) throws Exception {
		return executar(comando, requisicao, usuario, context);
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
	public Object executarSomenteLeitura(String comando, Object requisicao, Principal usuario, Context context) throws Exception {
		return executar(comando, requisicao, usuario, context);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
