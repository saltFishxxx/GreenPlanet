import cn.xyh.tree.dao.NewsDao;
import cn.xyh.tree.dao.daoImpl.NewsDaoImpl;
import cn.xyh.tree.domain.News;
import cn.xyh.tree.domain.News1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

public class NewsDaoTest {
    private NewsDao  newsDao = new NewsDaoImpl();

    //ok
    @Test
    public void testSelect1() {
        List<News1> all1 = newsDao.findAll1(null);
        for (News1 news1 : all1) {
            System.out.println(news1.getNewsContent());
            System.out.println(news1.getNewsDatetime());
            System.out.println(news1.getNewsType());
            System.out.println(news1.getNewsName());
            System.out.println(news1.getNewsLittletype());
            System.out.println(news1.getNewsId());
            System.out.println(news1.getNewsDatetime());
        }
    }

    //ok
    @Test
    public void testAddNews1() {
        News1 news1 = new News1();
        news1.setNewsContent("dksjflsdjflkdslfsdnvncxmvxkjlkjdfslkj");
        news1.setNewsDatetime(new Date());
        news1.setNewsName("测试新闻");
        news1.setNewsType("有趣");
        news1.setNewsLittletype("河道");
        int i = newsDao.addNews1(news1);
        System.out.println(i);
    }

    //ok
    @Test
    public void findNews1ById() {
        News1 news1ById = newsDao.findNews1ById(37);
        System.out.println(news1ById);
    }

    @Test
    public void testAddImgs() {
        int[] a= {15, 16};
        int i = newsDao.insertImgIds(a, 37);
        System.out.println(i);
    }

    //ok
    @Test
    public void testDeleteNews() {
        int i = newsDao.deleteNews(73);
        System.out.println(i);
    }

    @Test
    public void testupdateNews() {
        News1 news1 = new News1();
        news1.setNewsName("大哥");
        news1.setNewsId(74);
        news1.setNewsType("哈哈");
        news1.setNewsLittletype("环保");
        news1.setNewsContent("11111111111111111111");
        int i = newsDao.updateNews(news1);
        System.out.println(i);
    }

    @Test
    public void testFindQuickNews() {
        List<News1> quikNews = newsDao.findQuikNews();
        for (News1 news1 : quikNews) {
            System.out.println(news1);
        }

    }

}
