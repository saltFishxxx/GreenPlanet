package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.GoodDao;
import cn.xyh.tree.dao.NewsImgDao;
import cn.xyh.tree.dao.daoImpl.GoodDaoImpl;
import cn.xyh.tree.dao.daoImpl.NewsImgDaoImpl;
import cn.xyh.tree.domain.Good;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.domain.NewsImg;
import cn.xyh.tree.service.GoodService;
import cn.xyh.tree.util.toolImpl.DateUtil;

import java.util.Date;
import java.util.List;

public class GoodServiceImpl implements GoodService {
    private GoodDao goodDao = new GoodDaoImpl();
    private NewsImgDao newsImgDao = new NewsImgDaoImpl();

    @Override
    public boolean ifGood(Good good) {
        boolean flag = true;
        if(goodDao.ifGood(good) != null) {
            flag = false; //已点赞或收藏
        }
        return flag;
    }

    @Override
    public int goodCount(Good good) {
        return goodDao.goodCount(good);
    }

    @Override
    public boolean addGood(Good good) {
        boolean flag = false;
        if (ifGood(good)) {
            good.setGoodTime(new Date());
            goodDao.addGood(good);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<News1> findGooNewsByUid(String uid) {
        if ("".equals(uid) || uid == null) {
            return null;
        }
        List<News1> goodNews = goodDao.findGoodNews(Integer.parseInt(uid));
        for (News1 news : goodNews ) {
            List<NewsImg> newsImgs = newsImgDao.downLoad(news.getNewsId());
            news.setNewsImgs(newsImgs);
            news.setStringDateTime(DateUtil.DateToString(news.getNewsDatetime()));
        }
        return goodNews;
    }

    @Override
    public boolean deleteGood(Good good) {
        boolean flag = false;
        if (goodDao.deleteGood(good) > 0) {
            flag = true;
        }
        return flag;
    }
}
