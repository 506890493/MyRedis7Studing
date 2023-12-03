package com.geek.myredis7studing.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JedisSample {
    public static void main(String[] args) {
        //        Jedis jedis = new Jedis("172.17.0.2", 6379);
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.auth("123456");

        System.out.println(jedis.ping());

        Set<String> keys = jedis.keys("*");

        System.out.println(keys);

        String set = jedis.set("k1", "v1");
        System.out.println("execured resulst:"+set);

        System.out.println(jedis.setnx("k2", "v2"));

        String string = jedis.get("k1");
        System.out.println(string);

        System.out.println("time to live:"+jedis.ttl("k1"));


        System.out.println(jedis.lpush("llist1", "1", "2", "3", "4", "5"));

        jedis.lrange("list1", 0, -1).forEach(System.out::println);

        System.out.println(jedis.rpop("llist1"));

        //hash
        System.out.println("hash");

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("date","2023-12-02");
        stringStringHashMap.put("people","me and my wife");
        stringStringHashMap.put("dinner","tudoufeng/kaoroubanfa");
        jedis.hset("hset1",stringStringHashMap);

        System.out.println(jedis.hmget("hset1", "date", "people"));

        System.out.println(jedis.hexists("hset1", "date"));
        System.out.println(jedis.hkeys("hset1"));

        System.out.println("set");
        jedis.sadd("set1","1","2","3","0");
        jedis.sadd("set2","4");

        System.out.println(jedis.smembers("set1"));// 遍历显示 key中所有元素
        System.out.println(jedis.scard("set1"));//计算 set 集合中元素的个数
        System.out.println(jedis.spop("set1"));// 弹出最小 也是最左侧的元素
        jedis.smove("set1","set2","1");// 从src key 中 挪到target 元素到 dstkey
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.smembers("set2"));
        System.out.println(jedis.sinter("set1", "set2"));// 交集
        System.out.println(jedis.sunion("set1", "set2"));// 并集


        //zset
        System.out.println("zset");
        jedis.zadd("zset1",100,"v1");
        jedis.zadd("zset1",80,"v1");
        jedis.zadd("zset1",60,"v3");

        List<String> zset1 = jedis.zrange("zset1", 0, -1);

        for (String s : zset1) {
            System.out.println(s);
        }

        List<String> zset11 = jedis.zrevrange("zset1", 0, -1);
        for (String s : zset11) {
            System.out.println(s);
        }


    }
}
