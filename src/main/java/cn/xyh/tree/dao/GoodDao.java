package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Good;
import cn.xyh.tree.domain.News1;

import java.util.List;

public interface GoodDao {
    public Good ifGood(Good good); //是否点赞或收藏
    public int goodCount(Good good); //查看点赞或收藏的个数
    public int addGood(Good good); //添加收藏或点赞
    public List<News1> findGoodNews(int uid); //通过用户id查看收藏的文章
    public int deleteGood(Good good); //取消点赞或收藏
}
