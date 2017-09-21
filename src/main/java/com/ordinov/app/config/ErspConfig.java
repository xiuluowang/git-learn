package com.ordinov.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ordinov.app.db.HibernateTemplateConfiguration;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages={"com.ordinov.ersp","com.ordinov.app"},
		excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@Import(HibernateTemplateConfiguration.class)
public class ErspConfig {
}
