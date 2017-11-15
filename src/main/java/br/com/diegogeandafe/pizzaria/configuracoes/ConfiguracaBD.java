package br.com.diegogeandafe.pizzaria.configuracoes;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.diegogeandafe.pizzaria.modelo.repositorios")
public class ConfiguracaBD {
	
	//objeto datasource que corresponde ao bd
	//seta driver, jdbcurl, usuario e senha
	@Bean
	public DataSource dataSource() throws IllegalStateException, PropertyVetoException{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pizzaria");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	//container local, o objeto entityManagerFacotry passa como parametro o objeto dataSource, setando
	//os pacotes das entidades, provider do hibernate, e passando o dialeto do hibernate/jpa
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("br.com.diegogeandafe.pizzaria.modelo.entidades");
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
		
		//objeto jpaProperties seta dialeto do hibernate/mysql e configura autocriacao ou atualizacao pos execucao da aplicacao
		Properties jpaProterties = new Properties();
		jpaProterties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaProterties.put("hibernate.hbm2ddl.auto", "update");
		
		//jpa dialeto faz a traducao das excessoes do hibernate para as excessoes do spring
		entityManagerFactoryBean.setJpaProperties(jpaProterties);
		return entityManagerFactoryBean;
	}
	
	//gerencia transacoes do entityManager
	@Bean
	public JpaTransactionManager transactionManager() throws Exception {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
