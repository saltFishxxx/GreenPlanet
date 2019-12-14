package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.NewsImgDao;
import cn.xyh.tree.dao.daoImpl.NewsImgDaoImpl;
import cn.xyh.tree.domain.NewsImg;
import cn.xyh.tree.service.NewsImgService;
import cn.xyh.tree.service.NewsService;

public class NewsImgServiceImpl implements NewsImgService{
    private NewsImgDao newsImgDao = new NewsImgDaoImpl();

    @Override
    public int upload(NewsImg newsImg) {
        return newsImgDao.upload(newsImg);
    }
}
