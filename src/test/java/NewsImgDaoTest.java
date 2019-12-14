import cn.xyh.tree.dao.NewsImgDao;
import cn.xyh.tree.dao.daoImpl.NewsImgDaoImpl;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.domain.NewsImg;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NewsImgDaoTest {
    private NewsImgDao newsImgDao = new NewsImgDaoImpl();

    @Test
    public void testUpload() {
        NewsImg newsImg = new NewsImg();
        newsImg.setNewsImgUrl("sdkjfkjdsf.png");
        int upload = newsImgDao.upload(newsImg);
        System.out.println(upload);
    }

    @Test
    public void testDownlod() {
        List<NewsImg> newsImgs = newsImgDao.downLoad(37);
        for (NewsImg newsImg : newsImgs) {
            System.out.println(newsImg);
        }
    }


}
