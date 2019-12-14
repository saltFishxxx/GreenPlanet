package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.UserDao;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 三类用户，0普通，1管理员，2vip
 */
public class UserDaoImpl implements UserDao {
    //日志
    private static final Logger       log          = Logger.getLogger(UserDaoImpl.class);
    //使用了jdbcTemplate
    private              JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public User login(User user) {
        String sql = "select * from gp_user where user_name = ? and user_password = ? and user_authority in (0,2)";
        User sqlUser = null;
        //查询单一对象，如果为空时需要异常处理
//        if(checkUname(user.getUser_name())) {
        try {
            //new BeanPropertyRowMapper<User>(User.class)反射
            sqlUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUser_name(), user.getUser_password());
        } catch (DataAccessException e) {
            log.info("用户<" + user.getUser_name() + ">：账户密码不匹配");
        }
//        }else{
//            log.info("找不到<"+user.getUser_name()+">用户");
//        }
        return sqlUser;
    }

    @Override
    public User queById(String tel) {
        String sql = "select * from gp_user where user_tel = ? ";
        User sqlUser = null;
        try {
            //new BeanPropertyRowMapper<User>(User.class)反射
            sqlUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), tel);
        } catch (DataAccessException e) {}
        return sqlUser;
    }
    @Override
    public boolean register(User user) {
        boolean flag = false;
        String sql = "insert into gp_user(user_name, user_password, user_tel,user_email,user_sex,user_idnumber,user_code,user_imgs,user_city,user_nickname) values(?,?,?,?,?,?,?,?,?,?)";
        //更新行数
        int update = jdbcTemplate.update(sql, user.getUser_name(), user.getUser_password(),
                user.getUser_tel(), user.getUser_email(),
                user.getUser_sex(), user.getUser_idnumber(),
                user.getUser_code(), user.getUser_imgs(),
                user.getUser_city(), user.getUser_nickname());
        if (update > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updatePassword(User user) {
        boolean flag = false;
        String sql = "update gp_user set user_password = ? where user_id = ? and user_authority in (0,2)";
        //更新行数
        int update = jdbcTemplate.update(sql, user.getUser_password(), user.getUser_id());
        if (update > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean checkUname(String uname) {
        String sql = "select * from gp_user where user_name = ? and user_authority in (0,2)";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uname);
        } catch (Exception e) {

        }
        return user != null ? true : false; //存在用户返回true,不存在返回false
    }
//    @Override
//    public boolean checkUserTel(String Tel){
//        String sql = "selcet * from gp_user where user_tel=? and user_authority = 0";
//        User user = null ;
//        user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),Tel);
//        return user !=null?true :false ;
//    }

    /**
     * @param tel
     * @return 存在电话号码  true
     * 不存在电话号码 false
     */
    @Override
    public boolean checkTel(String tel) {
        String sql = "select user_tel from gp_user where user_tel = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), tel);
        }catch (Exception e){

        }
        return user != null ? true : false;
    }

    /**
     * 根据能量的排行查询用户的信息
     *
     * @return
     */
    @Override
    public List<User> userRankingList() {
        String sql = "SELECT u.* FROM gp_user u , gp_tree t  WHERE u.user_id = t.user_id  ORDER BY t.tree_power DESC";
        ArrayList<User> userList = null;

        userList = (ArrayList<User>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return userList;
    }

    /**
     * 实现vip的购买和到期
     * @param uid
     * @param status
     * @return
     */
    @Override
    public int updateUser(int uid, int status) {
        String sql = "update gp_user set user_authority = ? where user_id = ?";
        return jdbcTemplate.update(sql, status, uid);
    }

    //    //根据id查询用户排行
//    public User userRank(String userId){
//        String sql = ""
//    }
    //用户排行总人数
    public int rankCount(){
        String sql = "SELECT count(*) FROM gp_user u , gp_tree t  WHERE u.user_id = t.user_id  ORDER BY t.tree_power DESC";
        int count = 0 ;
        count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
}
