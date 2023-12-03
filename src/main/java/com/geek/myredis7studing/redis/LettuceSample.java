package com.geek.myredis7studing.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.List;

public class LettuceSample {
    public static void main(String[] args) {
        // 1 使用构建器链式编程来builder我们的RedisURI
        RedisURI redisURI = RedisURI.builder().
                withHost("127.0.0.1")
                .withPort(6379)
                .withAuthentication("default", "123456")
                .build();

        // 2 连接客户端
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();


        //3 创建操作的command ,通过conn 创建
        RedisCommands<String, String> command = connect.sync();

        // string
        command.set("k1","v1");
        System.out.println("======== mget  ======"+command.mget("k2"));
        System.out.println("======== get   ======"+command.get("k2"));

        List<String> keys = command.keys("*");
        for (String key : keys) {
            System.out.println("打印key:"+key);
        }

        // list

        command.lpush("list01","1","2","3","4");
        List<String> list01 = command.lrange("list01", 0, -1);
        for (String string : list01) {
            System.out.println(string);
        }

        System.out.println(command.rpop("list01", 2));

        //hash

        command.hset("hash","p1","v1");
        command.hset("hash","p2","v1");
        command.hset("hash","p3","v1");

        System.out.println(command.hgetall("hash"));

        Boolean hexists = command.hexists("hash", "v2");
        System.out.println(hexists);

        //set
        command.sadd("s1","1","2");

        System.out.println(command.smembers("s1"));
        System.out.println(command.sismember("s1", "1"));
        System.out.println(command.scard("s1"));

        //zset

        command.zadd("a1",100,"v1");
        command.zadd("a1",80,"v2");

        System.out.println(command.zrange("a1", 0, -1));
        System.out.println(command.zcount("a1", "90", "100"));

        connect.close();
        redisClient.shutdown();



    }
}
