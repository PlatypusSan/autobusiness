package com.test.autobusiness.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class MongoCache implements Cache {

    private final MongoTemplate mongoTemplate;

    @Getter
    @Setter
    private String name;

    @Override
    public Object getNativeCache() {
        return mongoTemplate.findAll(CacheObject.class, name);
    }

    @Override
    public ValueWrapper get(Object id) {

        Optional<Object> objectOptional = Optional.ofNullable(mongoTemplate.findById(id, CacheObject.class, name));

        CacheObject value = (CacheObject) objectOptional.orElse(null);
        ValueWrapper valueWrapper = value != null ? new SimpleValueWrapper(value.getCacheValue()) : null;

        if (valueWrapper != null)
            log.info("Cache was invoked: " + valueWrapper.get());
        return valueWrapper;
    }

    @Override
    public <T> T get(Object id, Class<T> aClass) {

        ValueWrapper valueWrapper = null;
        if (aClass.getName().equals("void")) {
            Optional<String> collectionName = Optional.of(aClass.getAnnotation(Document.class).collection());
            String name = collectionName.orElse("");

            Optional<CacheObject> optionalCacheObject = Optional.ofNullable(mongoTemplate.findById(id, CacheObject.class, name));
            CacheObject value = optionalCacheObject.orElse(null);
            valueWrapper = value != null ? new SimpleValueWrapper(value.getCacheValue()) : null;
            if (valueWrapper != null)
                log.info("Cache hit in test: " + valueWrapper.get());
        }
        return (T) valueWrapper;
    }

    @SneakyThrows
    @Override
    public <T> T get(Object id, Callable<T> callable) {

        CacheObject value = mongoTemplate.findById(id, CacheObject.class, name);
        return value != null ? (T) value.getCacheValue() : callable.call();
    }

    @Override
    public void put(Object id, Object value) {

        CacheObject cacheObject = new CacheObject(id, value);
        mongoTemplate.save(cacheObject, name);
        updateCache(id, value);
    }

    @Override
    public void evict(Object id) {

        evictByKey("_id", id);

        CacheObject cacheObject = mongoTemplate.findOne(new Query(Criteria.where("cacheValue._id").is(id)),
                CacheObject.class, name);

        if (cacheObject != null && cacheObject.getCacheValue() instanceof Collection) {
            mongoTemplate.updateMulti(new Query(),
                    new Update().pull("cacheValue", Query.query(Criteria.where("_id").is(id))), name);
        } else {
            evictByKey("cacheValue._id", id);
        }
    }

    private void evictByKey(String keyName, Object keyToDelete) {
        mongoTemplate
                .remove(new Query(Criteria.where(keyName).is(keyToDelete)),
                        CacheObject.class, name
                );
    }

    @Override
    public void clear() {
        mongoTemplate.getCollection(name).drop();
    }

    private void updateCache(Object key, Object value) {

        Query query = new Query(Criteria.where("cacheValue._id").is(key));
        Update cacheValue = new Update().set("cacheValue", value);

        mongoTemplate.updateMulti(query, cacheValue, name);
    }
}
