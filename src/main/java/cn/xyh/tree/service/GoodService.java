package cn.xyh.tree.service;

import cn.xyh.tree.domain.Good;
import cn.xyh.tree.domain.News1;

import java.util.List;

public interface GoodService {
    public boolean ifGood(Good good); //是否收藏或点赞
    public int goodCount(Good good); //收藏或点赞个数
    public boolean addGood(Good good); //点赞或收藏
    public List<News1> findGooNewsByUid(String uid); //返回用户收藏的新闻
    public boolean deleteGood(Good good); //取消点赞或收藏
}
