package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Activity;
import cn.xyh.tree.domain.ActivityUser;

import java.util.List;

public interface ActivityDao {
    public int addActivity(Activity activity); //添加活动
    public int deleteActivity(int aid); //删除活动
    public int updateActivity(); //更新活动
    public List<Activity> findActivitys(); //查询所有活动
    public int activityPCount(int aid); //参加活动人数
    public Activity findActivityById(int aid); //通过id查找活动
    public int signUp(int uid, int aid); //报名
    public ActivityUser ifSignUp(int uid, int aid); //是否报名
    public int deleteSignUp(int uid, int aid); //取消报名
    public List<Activity> findSignUpActivitys(int uid);
}
