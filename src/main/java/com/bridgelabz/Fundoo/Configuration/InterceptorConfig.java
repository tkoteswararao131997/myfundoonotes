package com.bridgelabz.Fundoo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bridgelabz.Fundoo.Utility.Interceptor;
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {
   @Autowired
   Interceptor interceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(interceptor);
   }
}