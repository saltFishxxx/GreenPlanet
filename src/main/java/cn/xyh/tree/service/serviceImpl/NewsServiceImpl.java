package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.GoodDao;
import cn.xyh.tree.dao.NewsDao;
import cn.xyh.tree.dao.NewsImgDao;
import cn.xyh.tree.dao.daoImpl.GoodDaoImpl;
import cn.xyh.tree.dao.daoImpl.NewsDaoImpl;
import cn.xyh.tree.dao.daoImpl.NewsImgDaoImpl;
import cn.xyh.tree.domain.Good;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.domain.NewsImg;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.NewsService;
import cn.xyh.tree.util.toolImpl.DateUtil;

import java.util.Date;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao = new NewsDaoImpl();
    private NewsImgDao newsImgDao = new NewsImgDaoImpl();
    private GoodDao goodDao = new GoodDaoImpl();

    @Override
    public boolean addNews(News1 news1) {
        boolean flag = false;
        int i = newsDao.addNews1(news1);
        List<NewsImg> newsImgs = news1.getNewsImgs();
        int[] imgIds = new int[newsImgs.size()];
        for (int j = 0; j < newsImgs.size(); j++) {
            imgIds[j] = newsImgs.get(j).getNewsImgId();
        }
        int i1 = newsDao.insertImgIds(imgIds, i);
        if (i1 > 0) {
            flag = true;
        }
        return flag;
    }

    //包括显示所有图片
    @Override
    public List<News1> findNews(String category) {
        List<News1> all1 = newsDao.findAll1(category);
        for (News1 news1 : all1) {
            //封装点赞信息
            Good good = new Good();
            good.setGoodType(1);
            good.setNews(news1);
            int i = goodDao.goodCount(good);
            news1.setGoodCount(i);
            List<NewsImg> newsImgs = newsImgDao.downLoad(news1.getNewsId());
            news1.setNewsImgs(newsImgs);
            news1.setStringDateTime(DateUtil.DateToString(news1.getNewsDatetime()));
        }
        return all1;
    }

    @Override
    public News1 findNewsById(String nid) {
        News1 news1 = null;
        if (nid != null && !"".equals(nid)) {
            news1 = newsDao.findNews1ById(Integer.parseInt(nid));
            if (news1 != null) {
                Good good = new Good();
                good.setGoodType(1);
                good.setNews(news1);
                int i = goodDao.goodCount(good);
                news1.setGoodCount(i);
                List<NewsImg> newsImgs = newsImgDao.downLoad(news1.getNewsId());
                news1.setNewsImgs(newsImgs);
                return news1;
            }
        }
        return news1;
    }

    @Override
    public List<News1> findQuikNews() {
        List<News1> quikNews = newsDao.findQuikNews();
        for (News1 news1 : quikNews) {
            List<NewsImg> newsImgs = newsImgDao.downLoad(news1.getNewsId());
            news1.setNewsImgs(newsImgs);
            news1.setStringDateTime(DateUtil.DateToString(news1.getNewsDatetime()));
        }
        return quikNews;
    }

    @Override
    public boolean updateNews(News1 news1) {
        boolean flag = false;
        if (newsDao.updateNews(news1) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deleteNews(String nid) {
        boolean flag = false;

        if (nid == null || "".equals(nid)) {
            return false;
        }

        if (newsDao.deleteNews(Integer.parseInt(nid)) > 0) {
            flag = true;
        }
        return flag;
    }
}
