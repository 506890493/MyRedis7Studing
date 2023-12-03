package com.geek.myredis7studing.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    public  final String ORDER_KEY="ord:";

    public void addOrder(){

        int i = ThreadLocalRandom.current().nextInt(1000);

        String randomid = UUID.randomUUID().toString();

        String key=ORDER_KEY+i;
        String value="京东订单"+randomid;

//   * this.redisTemplate.opsForValue(); //提供了操作string类型的所有方法
        redisTemplate.opsForValue().set(key,value);
        log.info("key:{}",key);
        log.info("value:{}",value);
//     * this.redisTemplate.opsForList(); // 提供了操作list类型的所有方法
//     * this.redisTemplate.opsForSet(); //提供了操作set的所有方法
//     * this.redisTemplate.opsForHash(); //提供了操作hash表的所有方法
//     * this.redisTemplate.opsForZSet(); //提供了操作zset的所有方法

    }

    public String getOrderId(Integer keyId){
        log.info("keyId:{}",keyId);
        return (String) redisTemplate.opsForValue().get(ORDER_KEY + keyId);
    }


}
