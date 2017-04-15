package br.com.servico.banco_dados;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
public class _TransactionManagementConfig {

	@Bean
	@DependsOn("userTransactionServiceImp")
	public PlatformTransactionManager transactionManager() {
		return new JtaTransactionManager(userTransaction(), userTransactionManager());
	}

	@Bean
	@DependsOn("userTransactionServiceImp")
	public UserTransaction userTransaction() {
		return new UserTransactionImp();
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
		return new UserTransactionServiceImp();
	}

}