package br.com.servico.banco_dados;

import java.util.Properties;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.config.imp.AbstractUserTransactionServiceFactory;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
public class _TransactionManagementConfig {

	@Bean
	@DependsOn("userTransactionServiceImp")
	public PlatformTransactionManager transactionManager() throws SystemException {
		JtaTransactionManager result = new JtaTransactionManager(userTransaction(), userTransactionManager());
		
		return result;
	}

	@Bean
	@DependsOn("userTransactionServiceImp")
	public UserTransaction userTransaction() throws SystemException {
		UserTransactionImp result = new UserTransactionImp();
		
		return result;
	}

	@Bean
	@DependsOn("userTransactionServiceImp")
	public TransactionManager userTransactionManager() {
		UserTransactionManager result = new UserTransactionManager();
		result.setStartupTransactionService(false);
		result.setForceShutdown(false);
		
		return result;
	}

	@Bean(name = "userTransactionServiceImp")
	public UserTransactionServiceImp userTransactionServiceImp() {
		UserTransactionServiceImp result = new UserTransactionServiceImp();
		
		// código para desligar o recurso de timeout do framework
		Properties properties = new Properties();
		properties.setProperty(AbstractUserTransactionServiceFactory.MAX_TIMEOUT_PROPERTY_NAME, "0");
		properties.setProperty(AbstractUserTransactionServiceFactory.DEFAULT_JTA_TIMEOUT_PROPERTY_NAME, "0");
		
		result.init(properties);

		return result;
	}

}