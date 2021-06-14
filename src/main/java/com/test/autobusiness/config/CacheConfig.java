package com.test.autobusiness.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.test.autobusiness.cache.CustomCacheResolver;
import com.test.autobusiness.cache.MongoCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {

        return Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {

        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("currencies");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    public MongoCacheManager mongoCacheManager() {
        return new MongoCacheManager();
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {

        Set<CacheManager> managers = new LinkedHashSet<>();
        managers.add(mongoCacheManager());
        return new CustomCacheResolver(managers);
    }

}
