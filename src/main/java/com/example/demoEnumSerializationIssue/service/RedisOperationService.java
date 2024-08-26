package com.example.demoEnumSerializationIssue.service;

import com.example.demoEnumSerializationIssue.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class RedisOperationService {
    @Autowired
    PlatformCache platformCache;

    public String getRedisConfig() {
        return platformCache.getRedisConfig();
    }

    public TransactionDto addTxn(String key) {
        final Map<String, TransactionDto> map = platformCache.getMap(key);
          /*
          Issue in Serialization/ DeSerialization of EnumMpa with Kyro5Codec
          Replacing EnumMap with HashMap works fine
         */
        //  Map<SettingType, Map<String, String>> settingTypeToSettingMap = new HashMap<>();  // This will work Fine
        Map<SettingType, Map<String, String>> settingTypeToSettingMap = new EnumMap<>(SettingType.class);
        TransactionDto transactionDto = fillData(settingTypeToSettingMap);

        map.put(key,transactionDto);
        platformCache.setTTL(key, 50000000L, TimeUnit.MILLISECONDS);
        Map<String, TransactionDto> map2 = platformCache.getMap(key);
        return map2.get(key);
    }

    private TransactionDto fillData(Map<SettingType, Map<String, String>> settingTypeToSettingMap) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        settingTypeToSettingMap.put(SettingType.SETTING_A,map1);
        return TransactionDto.builder().settingTypeToSettingMap(settingTypeToSettingMap).build();
    }

}
