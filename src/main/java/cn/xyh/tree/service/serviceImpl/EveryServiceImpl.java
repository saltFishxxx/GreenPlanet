package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.EverydayDao;
import cn.xyh.tree.dao.daoImpl.EveryDaoImpl;
import cn.xyh.tree.domain.EveryDay;
import cn.xyh.tree.service.EveryService;
import cn.xyh.tree.util.toolImpl.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


/**
 * 实现思路：
 */
public class EveryServiceImpl implements EveryService {
    private EverydayDao everydayDao = new EveryDaoImpl();

    @Override
    public String findEveryDay() {
        int length = 4; //定义每次容器存的长度
        Jedis jedis = JedisUtil.getJedis();
        //从容器中取所有的
        List<String> everyday = jedis.lrange("everyday", 0, -1);
        String everydaySentence = null;
        if (everyday == null || everyday.size() == 0 ) {
            List<EveryDay> all = everydayDao.findAll();
            if (all.size()<4) {
                everydayDao.restartAll();
                all = everydayDao.findAll();
            }
            Random random = new Random();
            int index = random.nextInt(all.size() - 3);
            String[] str = new String[length];
            //遍历，封装容器的语句
            int count = length; //计数
            ArrayList<Object[]> numbers = new ArrayList<>();  //所有数字
            for(int i = index; i < all.size(); i++){
                if (count <= 0) {
                    break;
                }
                str[i-index] = all.get(i).getEverydayContent();
                EveryDay everyDay = all.get(index);
                Object[] ints = new Object[1];
                ints[0] = everyDay.getEverydayId();
                numbers.add(ints);
//                everydayDao.updateStatus(everyDay.getEverydayId());
                count--;
            }
            everydayDao.updateStatus(numbers);
            jedis.lpush("everyday", str);
            everydaySentence = jedis.lpop("everyday");
        }else {
            everydaySentence = jedis.lpop("everyday");
        }
        if (jedis != null) {
            jedis.close();
        }
        return everydaySentence;
    }
}
