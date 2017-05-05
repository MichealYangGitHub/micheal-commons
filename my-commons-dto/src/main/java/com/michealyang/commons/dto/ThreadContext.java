package com.michealyang.commons.dto;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michealyang on 16/3/28.
 */
public class ThreadContext {
    private static final ThreadLocal<Map<Object, Object>> resources = new InheritableThreadLocalMap<Map<Object, Object>>();

    protected ThreadContext(){}

    public static Map<Object, Object> getResources(){
        return resources!=null ? new HashMap<>(resources.get()) : null;
    }

    public static void setResources(Map<Object, Object> newResources){
        if(CollectionUtils.isEmpty(newResources)) return;
        resources.get().clear();
        resources.get().putAll(newResources);
    }

    private static Object getValue(Object key){
        return resources.get().get(key);
    }

    public static Object get(Object key){
        return getValue(key);
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }

        resources.get().put(key, value);
    }

    public static Object remove(Object key) {
        Object value = resources.get().remove(key);
        return value;
    }

    public static void remove() {
        resources.remove();
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        protected Map<Object, Object> initialValue() {
            return new HashMap<Object, Object>();
        }

        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            if (parentValue != null) {
                return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }

}
