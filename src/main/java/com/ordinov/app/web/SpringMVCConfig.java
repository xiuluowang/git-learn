package com.ordinov.app.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ordinov.app.config.ErspConfig;
import com.ordinov.app.interceptor.LoginStatusInterceptor;

@Configuration
@EnableWebMvc
@Import(ErspConfig.class)
public class SpringMVCConfig extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
		registry.addResourceHandler("/site/**").addResourceLocations("/site/");
		registry.addResourceHandler("/*.html").addResourceLocations("/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registers = registry.addInterceptor(new LoginStatusInterceptor());
		registers.addPathPatterns("/api/**");
		registers.excludePathPatterns("/images/**","/js/**","/css/**","/fonts/**","/*.html","/login.html");
		
		super.addInterceptors(registry);
	}
	
	
}
