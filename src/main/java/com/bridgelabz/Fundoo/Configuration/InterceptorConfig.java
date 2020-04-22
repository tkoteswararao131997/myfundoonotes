package com.bridgelabz.Fundoo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bridgelabz.Fundoo.Interceptors.LoginInterceptor;
@SuppressWarnings("deprecation")
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {
   @Autowired
   LoginInterceptor logininterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(logininterceptor);
   }
}