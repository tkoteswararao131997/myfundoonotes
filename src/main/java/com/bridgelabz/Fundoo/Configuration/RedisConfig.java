package com.bridgelabz.Fundoo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.Fundoo.Entity.UserEntity;

public class RedisConfig {
	
	@Bean
	JedisConnectionFactory jedisconnection()
	{
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String,UserEntity> redisTemplate()
	{
		RedisTemplate<String,UserEntity> redistemplate=new RedisTemplate<>();
		redistemplate.setConnectionFactory(jedisconnection());
		return redistemplate;
	}

}
