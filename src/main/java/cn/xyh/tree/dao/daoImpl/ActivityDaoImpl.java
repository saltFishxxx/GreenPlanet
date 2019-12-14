package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.ActivityDao;
import cn.xyh.tree.domain.Activity;
import cn.xyh.tree.domain.ActivityUser;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ActivityDaoImpl implements ActivityDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public int addActivity(Activity activity) {
        String sql = "insert into gp_activity values (null, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, activity.getActivityName(), activity.getActivityPnumber(),
                activity.getActivityImg(), activity.getActivityLocation(), activity.getActivityContent(),
                activity.getActivityStart1(), activity.getActivityEnd1());
    }

    @Override
    public int deleteActivity(int aid) {
        String sql = "delete from gp_activity where activity_id = ?";
        return jdbcTemplate.update(sql, aid);
    }

    @Override
    public int updateActivity() {
        String sql = "update gp_activity ...";
        return 0;
    }

    @Override
    public List<Activity> findActivitys() {
        String sql = "select * from gp_activity";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Activity>(Activity.class));
    }

    @Override
    public int activityPCount(int aid) {
        String sql = "select count(*) from gp_activity_user where activity_id = ?";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class, aid);
        }catch (Exception e) {

        }
        return count;
    }

    @Override
    public Activity findActivityById(int aid) {
        String sql = "select * from gp_activity where activity_id = ?";
        Activity activity = null;
        try {
            activity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Activity>(Activity.class),
                    aid);
        }catch (Exception e) {

        }
        return activity;
    }

    @Override
    public int signUp(int uid, int aid) {
        String sql = "insert into gp_activity_user values(?,?)";
        return jdbcTemplate.update(sql, aid, uid);
    }

    @Override
    public ActivityUser ifSignUp(int uid, int aid) {
        String sql = "select * from gp_activity_user where user_id = ? and activity_id = ?";
        ActivityUser activityUser = null;
        try {
            activityUser = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<ActivityUser>(ActivityUser.class), uid, aid);
        }catch (Exception e) {

        }
        return activityUser;
    }

    @Override
    public int deleteSignUp(int uid, int aid) {
        String sql = "delete from gp_activity_user where activity_id = ? and user_id = ? ";
        return jdbcTemplate.update(sql, aid, uid);
    }

    @Override
    public List<Activity> findSignUpActivitys(int uid) {
        String sql = "select * from gp_activity where activity_id in (select activity_id from gp_activity_user where user_id = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Activity>(Activity.class), uid);
    }
}
