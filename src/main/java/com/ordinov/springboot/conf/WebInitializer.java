package com.ordinov.springboot.conf;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringMVCConfig.class);
		context.setServletContext(servletContext);
		
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST,
				DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC,DispatcherType.ERROR);
		servletContext.addFilter("addETagFilter", "org.springframework.web.filter.ShallowEtagHeaderFilter")
		.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
		//org.springframework.web.filter.ShallowEtagHeaderFilter dd;
	}

}
