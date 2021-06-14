package com.test.autobusiness.cache;

import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomCacheResolver implements CacheResolver {

    @Setter(onMethod = @__({@Autowired}))
    private MongoTemplate mongoTemplate;

    @Setter(onMethod = @__({@Autowired}))
    private MongoCacheManager mongoCacheManager;

    private Set<CacheManager> cacheManagers;
    private Collection<Cache> result;

    public CustomCacheResolver(Set<CacheManager> cacheManagers) {
        this.cacheManagers = cacheManagers;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {

        Set<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        result = new ArrayList<>(cacheNames.size());
        findCachesWithName(cacheNames, context);
        return result;
    }

    @Scope(scopeName = "prototype")
    public MongoCache mongoCache(String cacheName) {
        return new MongoCache(mongoTemplate, cacheName);
    }

    @SneakyThrows
    private void findCachesWithName(Set<String> cacheNames, CacheOperationInvocationContext<?> context) {

        cacheNames.forEach(cacheName -> {
            if (mongoCacheManager.getCache(cacheName) == null) {
                cacheManagers.forEach(cacheManager -> {
                    if (cacheManager instanceof MongoCacheManager)
                        ((MongoCacheManager) cacheManager).putCache(mongoCache(cacheName));
                });
            }
            cacheManagers.forEach(cacheManager -> {
                Cache cache = cacheManager.getCache(cacheName);
                if (isCacheHit(cache, context.getArgs())) {
                    result.add(cache);
                    return;
                }
                result.add(cache);
            });
        });
    }

    private boolean isCacheHit(Cache cache, Object[] args) {
        return Arrays.stream(args)
                .anyMatch(el -> cache.get(el) != null);
    }

    protected Set<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }
}
