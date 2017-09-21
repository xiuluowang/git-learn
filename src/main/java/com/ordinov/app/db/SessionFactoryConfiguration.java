package com.ordinov.app.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@Import(DataSourceConfiguration.class)
public class SessionFactoryConfiguration {
	
	@Bean
	public LocalSessionFactoryBean sessionFactry(DataSource ds){
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(ds);
		lsfb.setPackagesToScan(new String[]{"com.ordinov.ersp"});
		
		Properties hProperties = new Properties();
		hProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hProperties.setProperty("hibernate.show_sql", "true");
		hProperties.setProperty("hibernate.format_sql", "true");
		hProperties.setProperty("hibernate.generate_statistics", "false");
		hProperties.setProperty("hibernate.cache.use_query_cache", "false");
		hProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		
		lsfb.setHibernateProperties(hProperties);
		return lsfb;
	}
}
