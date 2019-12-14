package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.ActivityDao;
import cn.xyh.tree.dao.daoImpl.ActivityDaoImpl;
import cn.xyh.tree.domain.Activity;
import cn.xyh.tree.domain.ActivityUser;
import cn.xyh.tree.service.ActivityService;
import cn.xyh.tree.util.toolImpl.DateUtil;
import cn.xyh.tree.util.toolImpl.StrUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = new ActivityDaoImpl();

    @Override
    public HashMap<String, List<ActivityUser>> findActivitys() {
        //找到所有活动
        List<Activity> activitys = activityDao.findActivitys();
        //创建返回的分类List
        HashMap<String, List<ActivityUser>> stringListHashMap = new HashMap<>();
        for (Activity activity : activitys) {
            ActivityUser activityUser = new ActivityUser();
            activityUser.setActivity(activity);
            System.out.println(activity);
            activityUser.setPcount(activityDao.activityPCount(activity.getActivityId()));
            int status = DateUtil.CalStatus(activity.getActivityStart1(), activity.getActivityEnd1(), new Date());
            if (status == 0) {
                if (stringListHashMap.get("未开始") == null) {
                    ArrayList<ActivityUser> activityUsers = new ArrayList<>();
                    activityUsers.add(activityUser);
                    stringListHashMap.put("未开始", activityUsers);
                }else {
                    stringListHashMap.get("未开始").add(activityUser);
                }
            }else if (status == 1) {
                if (stringListHashMap.get("正在进行") == null) {
                    ArrayList<ActivityUser> activityUsers = new ArrayList<>();
                    activityUsers.add(activityUser);
                    stringListHashMap.put("正在进行", activityUsers);
                }else {
                    stringListHashMap.get("正在进行").add(activityUser);
                }
            }else if (status == 2) {
                if (stringListHashMap.get("已结束") == null) {
                    ArrayList<ActivityUser> activityUsers = new ArrayList<>();
                    activityUsers.add(activityUser);
                    stringListHashMap.put("已结束", activityUsers);
                }else {
                    stringListHashMap.get("已结束").add(activityUser);
                }
            }

        }
        return stringListHashMap;
    }

    @Override
    public ActivityUser findActivityById(String aid) {
        if (aid == null || "".equals(aid)) {
            return null;
        }
        Activity activityById = activityDao.findActivityById(Integer.parseInt(aid));
        activityById.setActivityStatus(DateUtil.CalStatus(activityById.getActivityStart1(), activityById.getActivityEnd1(), new Date()));
        ActivityUser activityUser = new ActivityUser();
        activityUser.setActivity(activityById);
        activityUser.setPcount(activityDao.activityPCount(Integer.parseInt(aid)));
        return activityUser;
    }

    @Override
    public boolean deleteActivity(String aid) {
        if (aid == null || "".equals(aid)) {
            return false;
        }
        boolean flag = false;
        if (activityDao.deleteActivity(Integer.parseInt(aid)) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean addActivity(Activity activity) {
        boolean flag = false;
        if (activityDao.addActivity(activity) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean ifSignUp(int uid, String aid) {
        if (aid == null || "".equals(aid)) {
            return false;
        }
        boolean flag = false;
        if (activityDao.ifSignUp(uid, Integer.parseInt(aid)) == null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean SignUp(int uid, String aid) {
        if (aid == null || "".equals(aid) || !ifSignUp(uid, aid)) {
            return false;
        }
        boolean flag = false;
        if (activityDao.signUp(uid, Integer.parseInt(aid)) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deleteSignUp(int uid, String aid) {
        if (StrUtil.ifNull(aid)) {
            return false;
        }

        boolean flag = false;
        if (activityDao.deleteSignUp(uid, Integer.parseInt(aid)) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<ActivityUser> findSignUpActivities(int uid) {
        List<Activity> signUpActivitys = activityDao.findSignUpActivitys(uid);
        ArrayList<ActivityUser> activityUsers = new ArrayList<>();
        for (Activity activity : signUpActivitys) {
            activity.setActivityStatus(DateUtil.CalStatus(activity.getActivityStart1(), activity.getActivityEnd1(), new Date()));
            ActivityUser activityUser = new ActivityUser();
            activityUser.setActivity(activity);
            activityUser.setPcount(activityDao.activityPCount(activity.getActivityId()));
            activityUsers.add(activityUser);
        }
        return activityUsers;
    }

    @Override
    public List<ActivityUser> findSignUpDuringActivities(int uid) {
        List<ActivityUser> signUpActivities = findSignUpActivities(uid);
        ArrayList<ActivityUser> newList = new ArrayList<>();
        for (ActivityUser upActivities : signUpActivities) {
            if (upActivities.getActivity().getActivityStatus() == 1) {
                newList.add(upActivities);
            }
        }
        return newList;
    }

    @Override
    public List<ActivityUser> findSignUpEndActivities(int uid) {
        List<ActivityUser> signUpActivities = findSignUpActivities(uid);
        List<ActivityUser> newList = new ArrayList<>();
        for (ActivityUser upActivities : signUpActivities) {
            if (upActivities.getActivity().getActivityStatus() == 2) {
                newList.add(upActivities);
            }
        }
        return newList;
    }
}
