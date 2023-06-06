package com.tibame.timetotravel.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
// 需要的話直接 @Autowired 注入 RedisTemplate<String, Object> redisTemplate;

    @Bean
    // 存入 Redis 的參數會以 String key, Object value 的形式，Object 在下方會轉成 JSON 格式
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // key 是 String
        template.setKeySerializer(new StringRedisSerializer());

        // value 轉 JSON
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // hash 型態也一起用了
        template.setHashKeySerializer(new StringRedisSerializer());

        // hash 型態也一起用了
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }

}
