package com.bridgelabz.Fundoo;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

//import com.bridgelabz.Fundoo.Configuration.ElasticSearchConfig;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Repository.UserRepository;
import com.bridgelabz.Fundoo.Service.CacheService;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@Slf4j
public class MyfundoonotesApplication implements CommandLineRunner{
	@Autowired
	private CacheService cacheService; 
	public static void main(String[] args) {
		SpringApplication.run(MyfundoonotesApplication.class, args);
		//log.info("Simple log statement with inputs {}, {} and {}", 1, 2, 3);
	}
	
	
//	private final org.slf4j.Logger LOG = LoggerFactory.getLogger(getClass());
//	  private UserRepository userRepository;
//
//	  @Autowired
//	  public MyfundoonotesApplication(UserRepository userRepository) {
//	    this.userRepository = userRepository;
//	  }

	  @Override
	  public void run(String... args) throws Exception {
	      String firstString = cacheService.cacheThis();
	      log.info("First: {}", firstString);
	      String secondString = cacheService.cacheThis();
	      log.info("Second: {}", secondString);
	  }
}
