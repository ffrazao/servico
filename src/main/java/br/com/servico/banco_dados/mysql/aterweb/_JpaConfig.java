package br.com.servico.banco_dados.mysql.aterweb;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

//@Profile("casa")
@Configuration(_JpaConfig._PERSISTENCE_UNIT_NOME + "Configuration")
@EnableJpaRepositories(basePackages = _JpaConfig.DAO, entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class _JpaConfig {

	public static final String _PERSISTENCE_UNIT_NOME = "aterweb";

	public static final String _PLATAFORMA = "mysql";

	public static final String DAO = "br.com.servico.banco_dados." + _PLATAFORMA + "." + _PERSISTENCE_UNIT_NOME + ".dao";

	public static final String MODELO = "br.com.servico.banco_dados." + _PLATAFORMA + "." + _PERSISTENCE_UNIT_NOME + ".modelo";

	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring." + _PERSISTENCE_UNIT_NOME + "Datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	@PersistenceContext(unitName = _PERSISTENCE_UNIT_NOME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()).persistenceUnit(_PERSISTENCE_UNIT_NOME).packages(MODELO).build();
	}
	
	@Primary
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

}