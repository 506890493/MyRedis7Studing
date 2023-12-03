package com.geek.myredis7studing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;
@Configuration
public class RedisClusterConfig {

    List<String> clusterNodes = Arrays.asList("127.0.0.1:30001", "127.0.0.1:30002", "127.0.0.1:30003");

    @Bean
    RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(new RedisClusterConfiguration(clusterNodes));
    }

    @Bean
    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        // just used StringRedisTemplate for simplicity here.
        return new StringRedisTemplate(factory);
    }
}
