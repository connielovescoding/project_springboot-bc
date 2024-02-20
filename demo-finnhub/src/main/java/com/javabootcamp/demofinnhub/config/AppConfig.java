package com.javabootcamp.demofinnhub.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.javabootcamp.demofinnhub.infra.RedisHelper;

@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    RedisHelper redisProfileHelper(RedisConnectionFactory factory) {
        return new RedisHelper(factory);
    }
    
}
