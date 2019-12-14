package cn.xyh.tree.service;

import cn.xyh.tree.domain.Activity;
import cn.xyh.tree.domain.ActivityUser;

import java.util.HashMap;
import java.util.List;

public interface ActivityService {
    public HashMap<String, List<ActivityUser>> findActivitys(); //查找所有活动信息
    public ActivityUser findActivityById(String aid); //根据活动id查看活动信息
    public boolean deleteActivity(String ad); //删除活动
    public boolean addActivity(Activity activity); //添加活动
    public boolean ifSignUp(int uid, String aid); //是否报名
    public  boolean SignUp(int uid, String aid); //报名活动
    public boolean deleteSignUp(int uid, String adi); //取消报名
    public List<ActivityUser> findSignUpActivities(int uid); //查找报名的活动
    public List<ActivityUser> findSignUpDuringActivities(int uid); //正在进行的报名活动
    public List<ActivityUser> findSignUpEndActivities(int uid); //已结束的报名活动
}
