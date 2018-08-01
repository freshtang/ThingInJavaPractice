package com.tjj.DistributedCache;

import com.ctg.itrdc.cache.pool.CtgJedisPool;
import com.ctg.itrdc.cache.pool.CtgJedisPoolConfig;
import com.ctg.itrdc.cache.pool.CtgJedisPoolException;
import com.ctg.itrdc.cache.pool.ProxyJedis;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class CtgJedisPoolTest {
    public static final Logger logger = LoggerFactory.getLogger(CtgJedisPoolTest.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        List<HostAndPort> hostAndPortList = new ArrayList(); //接入机地址列表
        HostAndPort host1 = new HostAndPort("132.122.232.225" ,8352); //接入机-1
        HostAndPort host2 = new HostAndPort("132.122.232.225" ,8362); //接入机-2
        hostAndPortList.add(host1);
        hostAndPortList.add(host2);

        GenericObjectPoolConfig poolConfig = new JedisPoolConfig(); //线程池配置
        poolConfig.setMaxIdle(3); //最大空闲连接数
        poolConfig.setMaxTotal(10); // 最大连接数（空闲+使用中）
        poolConfig.setMinIdle(3); //保持的最小空闲连接数
        poolConfig.setMaxWaitMillis(3000); //借出连接时最大的等待时间

        CtgJedisPoolConfig config = new CtgJedisPoolConfig(hostAndPortList);
        config.setDatabase(4970) //分组对应的桶位
                .setPassword("pool#Jedis*123") // “用户#密码”
                .setPoolConfig(poolConfig) //线程池配置
                .setPeriod(3000)  //后台监控执行周期，毫秒
                .setMonitorTimeout(200);  //后台监控ping命令超时时间,毫秒
        CtgJedisPool pool = new CtgJedisPool(config); //创建连接池
        ProxyJedis jedis = null;
        for (int i = 1; i <= 1000; i++) {

            try {
                jedis = pool.getResource();  //获取连接，可能抛出异常
                String key   = "**key" +i;
                String value = "**value" + i;
                jedis.set(key, value);
            } catch (CtgJedisPoolException e) {
                // e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
            finally {
                // finally内执行，确保连接归还
                try{
                    jedis.close();
                } catch (Throwable e) {

                }
                //归还连接至连接池
            }
        }
        pool.close(); //关闭连接池
    }
}