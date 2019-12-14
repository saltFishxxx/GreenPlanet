package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.UserDao;
import cn.xyh.tree.dao.daoImpl.UserDaoImpl;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        //校验用户名不为空，再进行登录的原子操作
        return userDao.checkUname(user.getUser_name()) == true ? userDao.login(user) : null;
    }

    @Override
    public boolean updatePassword(User user) {
        return userDao.updatePassword(user);
    }

    @Override
    public boolean register(User user) {
//        boolean checkUname = userDao.checkUname(user.getUser_name());
//        boolean checkTel = userDao.checkTel(user.getUser_tel());
//        if(checkTel==false &&checkUname==false){
//            return userDao.register(user);
//        }

        return ((userDao.checkUname(user.getUser_name()) == false) &&
                (userDao.checkTel(user.getUser_tel()) == false)) ?
                userDao.register(user) : false;
//        return  userDao.checkTel(user.getUser_tel()) == false ?
//                userDao.register(user) : false;
    }

    @Override
    public boolean checkUname(String uname) {
        return userDao.checkUname(uname);
    }

    @Override
    public boolean checkTel(String tel) {
        return userDao.checkTel(tel);
    }

    @Override
    public List<User> userRankingList() {
        return userDao.userRankingList();
    }

    @Override
    public int userRankById(User user){
//        user.getUser_id();
        int count = 1;
        List<User> userList = userDao.userRankingList();
        for (User u : userList){
            if (u.getUser_id()==user.getUser_id()){
                return count ;
            }
            count++;
        }
        return -1;
    }
    @Override
    public User queById(String tel){
        return userDao.queById(tel);
    }

}
