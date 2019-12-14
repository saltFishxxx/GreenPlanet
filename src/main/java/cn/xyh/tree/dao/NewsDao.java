package cn.xyh.tree.dao;

import cn.xyh.tree.domain.News;
import cn.xyh.tree.domain.News1;

import java.util.List;

public interface NewsDao {
    public List<News> findAll(); //查看所有新闻
    public List<News1> findAll1(String category); //查看，数据库
    public int addNews(News news); //添加新闻
    public int addNews1(News1 news); //添加新闻，存在数据库
    public News1 findNews1ById(int id); //通过文章id查询
    public int insertImgIds(int[] imgIds, int newsId); //插入图片的newsid
    public int deleteNews(int nid); //删除新闻
    public int updateNews(News1 news1); //修改新闻
    public List<News1> findQuikNews(); //速文推荐
}
