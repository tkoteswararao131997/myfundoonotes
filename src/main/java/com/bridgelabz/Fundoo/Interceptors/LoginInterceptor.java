package com.bridgelabz.Fundoo.Interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		log.info("Before Handler execution");
		String token=request.getHeader("token");
//		String url=request.getRequestURL().toString();
//		if(url.contains("user") || url.contains("swagger-ui"))
//		{
//				return true;
//		}
//		return false;
		return true;
		
	}
}