package com.relation.tag.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.Collections;
import java.util.List;

@Configuration
public class CommonConfig {
    @Value("${spring.redis.host}:${spring.redis.port}")
    private String redisUrl;
    @Value("${spring.redis.host:127.0.0.1}")
    private String redisHost;
    @Value("${spring.redis.port:6379}")
    private Integer redisPort;
    @Value("${spring.redis.password:}")
    private String redisPassword;
    @Value("${spring.redis.database:6379}")
    private Integer redisDatabase;
    @Value("${spring.redis.is-aws:false}")
    private Boolean isAws;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        if (isAws) {
            List<String> nodes = Collections.singletonList(redisUrl);
            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes);
            return new LettuceConnectionFactory(clusterConfiguration);
        } else {

            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
            redisStandaloneConfiguration.setHostName (redisHost);
            redisStandaloneConfiguration.setPort (redisPort);
            redisStandaloneConfiguration.setPassword (redisPassword);
            redisStandaloneConfiguration.setDatabase (redisDatabase);
            return new LettuceConnectionFactory(redisStandaloneConfiguration);
        }
    }
}
