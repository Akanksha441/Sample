package com.example.demoEnumSerializationIssue.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.redisson.Redisson;


@Service
@Slf4j
@AllArgsConstructor
public class PlatformCache {

    private RedissonClient client;


    public <K, V> Map<K, V> getMap(final String cacheKey) {
        final RMap<K, V> rMap = client.getMap(cacheKey);
        return rMap;
    }
    public boolean setTTL(final String cacheKey, final long ttl, final TimeUnit timeUnit) {
        RKeys rKeys = this.client.getKeys();
        return rKeys.expire(cacheKey, ttl, timeUnit);
    }

    public String getRedisConfig() {
       return client.getConfig().getCodec().toString() ;
    }
}