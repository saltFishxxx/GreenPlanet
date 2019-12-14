package cn.xyh.tree.service;

import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;

public interface TreeService {
    boolean addEnergy(User user, int energy);

    //    boolean addTree(Tree tree, int userId)
    boolean addTree(Tree tree, User user);

    Tree queryTreeById(User user);
}
