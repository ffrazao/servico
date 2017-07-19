package br.com.servico.banco_dados.sqlserver.banco1;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Profile("!casa")
@Configuration(_JpaConfig._PERSISTENCE_UNIT_NOME + "Configuration")
@EnableJpaRepositories(basePackages = _JpaConfig.DAO, entityManagerFactoryRef = _JpaConfig._PERSISTENCE_UNIT_NOME + "EntityManagerFactory", transactionManagerRef = "transactionManager")
public class _JpaConfig {

	public static final String _PERSISTENCE_UNIT_NOME = "scie";

	public static final String _PLATAFORMA = "sqlserver";

	public static final String DAO = "br.com.servico.banco_dados." + _PLATAFORMA + "." + _PERSISTENCE_UNIT_NOME + ".dao";

	public static final String MODELO = "br.com.servico.banco_dados." + _PLATAFORMA + "." + _PERSISTENCE_UNIT_NOME + ".modelo";

	@Bean(name = _PERSISTENCE_UNIT_NOME + "DataSource")
	@ConfigurationProperties(prefix = "spring." + _PERSISTENCE_UNIT_NOME + "Datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = _PERSISTENCE_UNIT_NOME + "EntityManagerFactory")
	@PersistenceContext(unitName = _PERSISTENCE_UNIT_NOME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()).persistenceUnit(_PERSISTENCE_UNIT_NOME).packages(MODELO).build();
	}

}