package com.test.autobusiness.cache;

import lombok.Data;

import javax.persistence.Id;
import java.util.Objects;

@Data
public class CacheObject {

    @Id
    private Object id;
    private Object cacheValue;

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
