package cn.xyh.tree.dao;

import cn.xyh.tree.domain.News1;
import cn.xyh.tree.domain.User;

import java.util.List;

public interface UserDao {
    public User login(User user); //登陆

    User queById(String tel);

    public boolean register(User user); //注册
    public boolean updatePassword(User user); //更改密码，通过获取id和密码来修改密码
    public boolean checkUname(String uname); //检查用户名是否存在

//    boolean checkUserTel(String Tel);

    public boolean checkTel(String tel);    //检查电话号码是否存在

    List<User> userRankingList();
    public int updateUser(int uid,int status); //更新用户的权限状态
}
