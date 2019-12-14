package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Garbage;

import java.util.List;

public interface GarbageDao {
    public List<Garbage> selectAll(); //查找所有垃圾通的位置
    public int deleteGarbage(int gid); //删除垃圾桶位置
    public int updateGarbage(Garbage garbage); //更新垃圾桶位置
    public int addGarbage(Garbage garbage); //添加垃圾桶位置
}
