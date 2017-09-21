package com.ordinov.app.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ordinov.app.utils.RequestUtils;
import com.ordinov.ersp.user.entity.User;

public class LoginStatusInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 日志记录器
	 */
	private static final Logger LOGGER = Logger.getLogger(LoginStatusInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LOGGER.debug("LoginStatus:" + request.getRequestURI());
		User user = (User) request.getSession().getAttribute("user");
		boolean allowed = false;
		if(user == null){
			LOGGER.debug("you have not login,Please login first!");
			allowed = false;
		}else{
			LOGGER.debug("you have login!");
			allowed = true;
		}
		
		String destUrl = null;
		/**
		 * 如果请求是ajax请求
		 */
		if(RequestUtils.isAjaxRequest(request)){
			//destUrl = request.getHeader("Referer");
			//destUrl = URLEncoder.encode((destUrl==null)?"":destUrl,"utf-8");
			LOGGER.debug("This is a non-ajax request.");
		}else{
			destUrl = URLEncoder.encode(request.getRequestURL().toString(),"utf-8");
			response.sendRedirect(request.getContextPath() +"/login.html?code=1&url=" + destUrl );
		}
		
		return allowed;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("-----BBBB----");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		LOGGER.debug("-----CCCC----");
		super.afterCompletion(request, response, handler, exception);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LOGGER.debug("-----DDDD----");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
