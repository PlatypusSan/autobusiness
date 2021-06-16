package com.test.autobusiness.cache;

import javax.persistence.Id;
import java.util.Objects;

public class CacheObject {

    @Id
    private final Object id;
    private final Object cacheValue;

    public CacheObject(Object id, Object cacheValue) {
        this.id = id;
        this.cacheValue = cacheValue;
    }

    public Object getCacheValue() {
        return cacheValue;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheObject that = (CacheObject) o;
        return Objects.equals(cacheValue, that.cacheValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cacheValue);
    }
}
