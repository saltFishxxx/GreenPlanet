import cn.xyh.tree.dao.ActivityDao;
import cn.xyh.tree.dao.daoImpl.ActivityDaoImpl;
import cn.xyh.tree.domain.Activity;
import cn.xyh.tree.domain.ActivityUser;
import cn.xyh.tree.util.toolImpl.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ActivityDaoTest {
    private ActivityDao activityDao = new ActivityDaoImpl();

    @Test
    public void testAddActivity() {
        Activity activity = new Activity();
        activity.setActivityContent("fsddsfsdf");
        activity.setActivityEnd1(DateUtil.StringToDate("2019-3-22 12:22:12"));
        activity.setActivityStart1(DateUtil.StringToDate("2019-3-11 17:11:55"));
        activity.setActivityImg("sfskdjflkjsdfk/jfljds/jsdfl.png");
        activity.setActivityLocation("北京");
        activity.setActivityName("植树活动");
        activity.setActivityPnumber(33);
        int i = activityDao.addActivity(activity);
        System.out.println(i);
    }

    @Test
    public void testDeleteActivity() {
        int i = activityDao.deleteActivity(1);
        System.out.println(i);
    }

    @Test
    public void testFindActivitys() {
        List<Activity> activitys = activityDao.findActivitys();
        System.out.println(activitys);
    }

    @Test
    public void testUpdateActivity() {

    }

    @Test
    public void testActivityCount() {
        int i = activityDao.activityPCount(2);
        System.out.println(i);
    }

    @Test
    public void tesSignUp() {
        int i = activityDao.signUp(24, 8);
        System.out.println(i);
    }

    @Test
    public void testIfSignUp() {
        ActivityUser activityUser = activityDao.ifSignUp(24, 8);
        System.out.println(activityUser);
        if (activityUser == null) {
            System.out.println("没查到");
        }
    }

    @Test
    public void testDeleteSignUp() {
        int i = activityDao.deleteSignUp(24, 8);
        System.out.println(i);
    }

    @Test
    public void testFindSignUpActivities() {
        List<Activity> signUpActivitys = activityDao.findSignUpActivitys(28);
        System.out.println(signUpActivitys);
    }
}
