package com.gy.conf;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * RedisTemplate 不配置RedisTemplate 可以正常使用，但数据并未存入redis
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		// 1.创建 redisTemplate 模版
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		// 2.关联 redisConnectionFactory
		template.setConnectionFactory(redisConnectionFactory);
		// 3.创建 序列化类
		GenericToStringSerializer<?> genericToStringSerializer = new GenericToStringSerializer<>(Object.class);
		// 6.序列化类，对象映射设置
		// 7.设置 value 的转化格式和 key 的转化格式
		template.setValueSerializer(genericToStringSerializer);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(genericToStringSerializer);
		template.setHashValueSerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}
}