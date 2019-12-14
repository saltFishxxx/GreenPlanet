package cn.xyh.tree.util.toolImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * jedis工具类
 */
public class JedisUtil{
    private static JedisPool jedisPool;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大链接数
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        //最大闲置链接数
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));

        jedisPool = new JedisPool(jedisPoolConfig, properties.getProperty("host"),
                Integer.parseInt(properties.getProperty("post")));
    }


    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void closeJedis(Jedis jedis) {
        jedis.close();
    }
}
