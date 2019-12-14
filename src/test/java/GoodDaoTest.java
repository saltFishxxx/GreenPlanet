import cn.xyh.tree.dao.GoodDao;
import cn.xyh.tree.dao.daoImpl.GoodDaoImpl;
import cn.xyh.tree.domain.Good;
import cn.xyh.tree.domain.News;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class GoodDaoTest {
    private GoodDao goodDao = new GoodDaoImpl();

    @Test
    public void testIfGood() {
        Good good = new Good();
        good.setGoodType(1);
        User user = new User();
        user.setUser_id(1);
        News1 news1 = new News1();
        news1.setNewsId(37);
        good.setUser(user);
        good.setNews(news1);
        Good good1 = goodDao.ifGood(good);
        System.out.println(good1);
    }

    @Test
    public void testAddGood() {
        Good good = new Good();
        good.setGoodTime(new Date());
        good.setGoodType(1);
        User user = new User();
        user.setUser_id(1);
        News1 news1 = new News1();
        news1.setNewsId(64);
        good.setUser(user);
        good.setNews(news1);
        System.out.println(goodDao.addGood(good));
    }

    @Test
    public void testGoodCount() {
        Good good = new Good();
        good.setGoodType(1);
        User user = new User();
        user.setUser_id(1);
        News1 news1 = new News1();
        news1.setNewsId(37);
        good.setUser(user);
        good.setNews(news1);
        System.out.println(goodDao.goodCount(good));
    }

    @Test
    public void testDeleteGood() {
        Good good = new Good();
        User user = new User();
        user.setUser_id(1);
        good.setUser(user);
        News1 news1 = new News1();
        news1.setNewsId(64);
        good.setNews(news1);
        good.setGoodType(1);
        int i = goodDao.deleteGood(good);
        System.out.println(i);
    }

    @Test
    public void testFindGoodNews() {
        List<News1> goodNews = goodDao.findGoodNews(1);
        System.out.println(goodNews.size());
    }
}
