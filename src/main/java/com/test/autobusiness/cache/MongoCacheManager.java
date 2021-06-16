package com.test.autobusiness.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.*;

public class MongoCacheManager extends AbstractCacheManager {

    private final List<Cache> caches = new ArrayList<>();
    private final Map<Object, MongoCache> cacheMap = new HashMap<>();

    public void putCache(MongoCache cache) {
        cacheMap.put(cache.getName(), cache);
    }

    @Override
    public Cache getCache(String name) {

        if (cacheMap.containsKey(name))
            return cacheMap.get(name);
        return super.getCache(name);
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return caches;
    }
}