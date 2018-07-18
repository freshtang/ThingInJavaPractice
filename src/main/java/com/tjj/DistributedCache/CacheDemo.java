package com.tjj.DistributedCache;

import redis.clients.jedis.Jedis;

public class CacheDemo {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("138.68.24.50", 6379);
        jedis.set("name", "tangjunjian");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("name"));

    }
}
