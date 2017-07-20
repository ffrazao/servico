package br.com.servico.banco_dados.h2.padrao;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Profile("casa")
@Configuration(_JpaConfig._PERSISTENCE_UNIT_NOME + "Configuration")
@EnableJpaRepositories(basePackages = _JpaConfig.DAO, entityManagerFactoryRef = _JpaConfig._PERSISTENCE_UNIT_NOME + "EntityManagerFactory", transactionManagerRef = "transactionManager")
public class _JpaConfig {

	public static final String _PERSISTENCE_UNIT_NOME = "padrao";

	public static final String _PLATAFORMA = "h2";

	public static final String DAO = "br.com.servico.banco_dados." + _PLATAFORMA + "." + _PERSISTENCE_UNIT_NOME + ".dao";

	public static final String MODELO = "br.com.servico.banco_dados." + _PLATAFORMA + "." + _PERSISTENCE_UNIT_NOME + ".modelo";
	
	@Value("${init-db:false}")
	private String initDatabase;
	
	@Bean
	@Autowired
	public DataSourceInitializer dataSourceInitializer(@Qualifier(_PERSISTENCE_UNIT_NOME + "DataSource") DataSource dataSource) {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("script/h2/padrao/import.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
		return dataSourceInitializer;
	}

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
	
	@Bean(name = _PERSISTENCE_UNIT_NOME + "JdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

}