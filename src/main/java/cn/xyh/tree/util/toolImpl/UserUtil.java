package cn.xyh.tree.util.toolImpl;

import cn.xyh.tree.domain.User;
import redis.clients.jedis.Jedis;

public class UserUtil {
    private UserUtil() {}
    public static Integer getUserId() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            byte[] bytes = jedis.get("user".getBytes());
            User user = (User)SerializableUtil.unserizlize(bytes);
            return user.getUser_id();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
}
