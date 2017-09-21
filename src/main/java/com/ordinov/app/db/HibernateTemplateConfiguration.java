package com.ordinov.app.db;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTemplate;

@Configuration
@Import(SessionFactoryConfiguration.class)
public class HibernateTemplateConfiguration {

	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory){
		return new HibernateTemplate(sessionFactory);
	} 
}
