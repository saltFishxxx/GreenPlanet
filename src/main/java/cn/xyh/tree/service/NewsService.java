package cn.xyh.tree.service;

import cn.xyh.tree.domain.News;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.domain.NewsImg;

import java.util.List;

public interface NewsService {
    public boolean addNews(News1 news1); //添加文章
    public List<News1> findNews(String category); //根据文章类别查询
    public News1 findNewsById(String nid); //根据文章id查询
    public List<News1> findQuikNews(); //速文推荐
    public boolean updateNews(News1 news1); //更改新闻
    public boolean deleteNews(String nid); //删除新闻
}
