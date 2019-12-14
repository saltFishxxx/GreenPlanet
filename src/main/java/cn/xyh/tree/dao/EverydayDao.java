package cn.xyh.tree.dao;

import cn.xyh.tree.domain.EveryDay;

import java.util.List;

public interface EverydayDao {
    public List<EveryDay> findAll(); //查询所有的每日一句
    public int restartAll(); //重置所有的状态
    public int[] updateStatus(List<Object[]> eid); //更新状态，说明已经添加进容器
}
