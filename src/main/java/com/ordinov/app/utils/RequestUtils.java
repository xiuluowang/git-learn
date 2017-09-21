package com.ordinov.app.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 请求处理工具类
 * @author dengfx
 *
 */
public final class RequestUtils {
	
	/**
	 * 显示请求头
	 */
	private static boolean showRequestHeader = false;
	
	/**
	 * 日志记录器
	 */
	private static final Logger LOGGER = Logger.getLogger(RequestUtils.class);
	
	/**
	 * 显示格式器
	 */
	private static final String HTTP_HEAD_FORMAT = "%-30s = %-50s\n";
	
	/**
     * 打印所有HTTP头
     * @param request
     */
    public static void print(final HttpServletRequest request){
    	if(showRequestHeader){
    		StringBuilder info = new StringBuilder(request.getSession().getId() + " 请求HTTP头信息\n");
    		Enumeration<String> headers = request.getHeaderNames();
    		while(headers.hasMoreElements()){
    			String headerName = headers.nextElement();    			
    			info.append(String.format(HTTP_HEAD_FORMAT,headerName,request.getHeader(headerName)));
    		}
    		info.append("地址类信息:\n");
    		info.append(String.format(HTTP_HEAD_FORMAT,"RemoteAddr",request.getRemoteAddr()));
    		info.append(String.format(HTTP_HEAD_FORMAT,"RemoteHost", request.getRemoteHost()));
    		info.append(String.format(HTTP_HEAD_FORMAT,"x-forwarded-for", request.getHeader("x-forwarded-for")));
    		info.append(String.format(HTTP_HEAD_FORMAT,"x-forwarded-by", request.getHeader("x-forwarded-by")));
    		info.append(String.format(HTTP_HEAD_FORMAT,"Proxy-Client-IP",request.getHeader("Proxy-Client-IP")));
    		info.append(String.format(HTTP_HEAD_FORMAT,"WL-Proxy-Client-IP" ,request.getHeader("WL-Proxy-Client-IP")));
    		info.append(String.format(HTTP_HEAD_FORMAT,"HTTP_CLIENT_IP" , request.getHeader("HTTP_CLIENT_IP")));
    		info.append(String.format(HTTP_HEAD_FORMAT,"HTTP_X_FORWARDED_FOR" , request.getHeader("HTTP_X_FORWARDED_FOR")));
    		
    		LOGGER.debug(info.toString());
    	}
    }
    
	/**
	 * 查看该请求是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(final HttpServletRequest request){
	    boolean isAjax = false;
        if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
            isAjax = true;
        }
        return isAjax;
	}
}
