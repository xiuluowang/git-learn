package com.ordinov.app.transaction;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

@Configuration
public class AppTransactionConfig {
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		return new HibernateTransactionManager(sessionFactory);
	}
	
}
