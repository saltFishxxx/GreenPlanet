import cn.xyh.tree.dao.UserDao;
import cn.xyh.tree.dao.daoImpl.UserDaoImpl;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.UserService;
import cn.xyh.tree.service.serviceImpl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 测试用户数据层方法
 */

public class UserDaoTest {
    private UserDao     userDao = new UserDaoImpl();
    private UserService userService = new UserServiceImpl();
    //ok
    @Test
    public void testLogin() {
        User user = new User();
        user.setUser_name("小红");
        user.setUser_password("222222");
        User login = userDao.login(user);
        System.out.println(login);
    }
    @Test//ok
    public void userRankingList(){

        List<User> users = userService.userRankingList();
        List<HashMap<String,String>> userRankingList = new ArrayList<>();
        for (User u : users){
            HashMap<String, String> map = new HashMap<>();
            map.put("openid",u.getUser_code());
//            map.put("user_imgs",u.getUser_imgs());
//            map.put("user_city",u.getUser_city());
//            map.put("user_nickname",u.getUser_nickname());
//            map.put("rank",userDao.userRankingList())
            userRankingList.add(map);
        }
        JSONArray array  = new JSONArray();
        array.add(userRankingList);
        System.out.println(array.toString());
//        int anInt = array.getInt(0);
//        System.out.println(anInt);
//        array.getInt(1);
//        System.out.println(users.toString());

    }

    @Test
    public void userRank(){

        List<User> users = userService.userRankingList();
        List<HashMap<String,String>> userRankingList = new ArrayList<>();
        for (User u : users){
            HashMap<String, String> map = new HashMap<>();
            map.put("openid",u.getUser_code());
//            map.put("user_imgs",u.getUser_imgs());
//            map.put("user_city",u.getUser_city());
//            map.put("user_nickname",u.getUser_nickname());
            userRankingList.add(map);
        }
        JSONArray array  = new JSONArray();
        array.add(userRankingList);
        System.out.println(array.toString());


    }



    //ok
    @Test
    public void testRegister() {
        User user = new User();
        user.setUser_name("qqssqq112233");
        user.setUser_password("123456");
        user.setUser_sex("男");
        user.setUser_email("516516851@qq.com");
        user.setUser_idnumber("5003206906090496x");
        user.setUser_tel("12311321321");
        boolean register = userDao.register(user);
        System.out.println(register);
    }

    //ok
    @Test
    public void testUpdatePassword() {
        User user = new User();
        user.setUser_id(26);
        user.setUser_password("333333");
        boolean b = userDao.updatePassword(user);
        System.out.println(b);
    }

    //ok
    @Test
    public void testCheckUname() {
        boolean uname = userDao.checkUname("q1134248919");
        System.out.println(uname);
    }
    @Test
    public void queById(){
        User user = userService.queById("18983592583");
        JSONObject jsonObject = JSONObject.fromObject(user);
        System.out.println(jsonObject);
    }
    @Test
    public void testCheckTel() {
        boolean Tel = userDao.checkTel("1123451515526");
        System.out.println(Tel);
    }

    @Test
    public void testUpdateStatus() {
        int i = userDao.updateUser(27, 2);
        System.out.println(i);
    }
}
