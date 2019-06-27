package com.alexanderberndt.aem.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ConfigBuilder<T> {

    private final Class<T> type;

    private Map<String, Object> valueMap = new HashMap<>();

    private ConfigBuilder(Class<T> type) {
        this.type = type;
    }

    public static <T> ConfigBuilder<T> of(Class<T> type) {
        return new ConfigBuilder<>(type);
    }

    public ConfigBuilder<T> set(String key, Object value) {
        this.valueMap.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public T build() {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, new ConfigProxy(valueMap));
    }

    private static class ConfigProxy implements InvocationHandler {

        final Map<String, Object> valueMap;

        private ConfigProxy(Map<String, Object> valueMap) {
            this.valueMap = valueMap;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // return, if defined
            if (valueMap.containsKey(method.getName())) {
                return valueMap.get(method.getName());
            } else {
                return method.getDefaultValue();
            }
        }
    }
}


