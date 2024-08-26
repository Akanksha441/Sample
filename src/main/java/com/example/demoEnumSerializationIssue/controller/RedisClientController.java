package com.example.demoEnumSerializationIssue.controller;

import com.example.demoEnumSerializationIssue.model.TransactionDto;
import com.example.demoEnumSerializationIssue.service.RedisOperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/redisson")
public class RedisClientController {

    private RedisOperationService redisOperationService;
    public static String CACHE_KEY = "Cache_Key";


    @PostMapping("/testTransaction/{key}")
    TransactionDto doTransaction(@PathVariable String key){
        TransactionDto transactionDto = redisOperationService.addTxn(key);
        return transactionDto;
    }
    @GetMapping("/rediConfig")
    String rediConfig(){
        return redisOperationService.getRedisConfig();
    }

}