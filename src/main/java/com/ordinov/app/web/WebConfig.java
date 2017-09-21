package com.ordinov.app.web;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext servletContext)
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
		
		//System.out.println("Virtual Server Name is " + servletContext.getVirtualServerName());
	
		javax.servlet.FilterRegistration.Dynamic filter = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "utf-8");
		filter.addMappingForUrlPatterns(null, false, "/*");
		
		//针对put请求在控制器中获取参数数据
		javax.servlet.FilterRegistration.Dynamic putfilter = servletContext.addFilter("httpPutFormContentFilter", HttpPutFormContentFilter.class);
		putfilter.addMappingForUrlPatterns(null, false, "/*");
		
	}

}
