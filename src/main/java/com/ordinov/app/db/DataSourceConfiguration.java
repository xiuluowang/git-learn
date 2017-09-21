package com.ordinov.app.db;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据源配置类
 * @author dengfx
 *
 */
@Configuration
public class DataSourceConfiguration {
	
	@Bean
	public DataSource dataSourceOfDBCP() throws PropertyVetoException{
		ComboPooledDataSource ds = new ComboPooledDataSource(); 
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://192.168.1.180:3306/oap3_ersp?characterEncoding=UTF-8&useUnicode=true");
		ds.setUser("ersp");
		ds.setPassword("ersp");
		return ds;
	}
}
