import cn.xyh.tree.domain.User;
import cn.xyh.tree.util.toolImpl.JedisUtil;
import cn.xyh.tree.util.toolImpl.SerializableUtil;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class SerializeTest {

    @Test
    public void serializeTest(){
//        Jedis jedis = new Jedis("120.79.240.176",6379);
        Jedis jedis = JedisUtil.getJedis();
        User user = new User();
        user.setUser_id(1);
        user.setUser_sex("男");

        jedis.set("user".getBytes(), SerializableUtil.serialize(user));
        byte[] byt = jedis.get("user".getBytes());
        Object object = SerializableUtil.unserizlize(byt);
        if(object instanceof User){
            System.out.println(object);
        }
        jedis.close();
    }
}
