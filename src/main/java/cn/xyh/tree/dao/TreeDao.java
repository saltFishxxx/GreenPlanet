package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;

public interface TreeDao {

//    boolean addEnergy(User user, int energy);

    boolean addEnergy(int userId, int energy);

    boolean addTree(Tree tree, int userId);

    boolean delTreeById(int userId);

    Tree queryTreeById(int userId);

    boolean checkTree(int userId);
}
