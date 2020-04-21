package com.bridgelabz.Fundoo.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CacheService {

    @Cacheable(cacheNames = "myCache")
    public String cacheThis(){
        log.info("Returning NOT from cache!");
        return "this Is it";
    }
}