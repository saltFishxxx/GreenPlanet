package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Card;

import java.util.List;

public interface CardDao {
    // 增加card
    boolean addCardById(Card card);

    // 删除card未完成
    boolean delCardById(Card card);

    // 修改card未完成
    boolean updateCardById(Card card);

    //查询
    List<Card> queyCardById(String UserId);
}
