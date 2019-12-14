package cn.xyh.tree.service;

import cn.xyh.tree.domain.User;

import java.util.List;

public interface UserService {
    public User login(User user); //登陆
    public boolean updatePassword(User user); //更新密码
    public boolean register(User user); //注册
    public boolean checkUname(String uname); //查询用户名是否存在
    public boolean checkTel(String tel);

    List<User> userRankingList();

    int userRankById(User user);

    User queById(String tel);
}
