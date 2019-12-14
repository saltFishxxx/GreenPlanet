package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.*;
import cn.xyh.tree.service.ActivityService;
import cn.xyh.tree.service.serviceImpl.ActivityServiceImpl;
import cn.xyh.tree.util.toolImpl.*;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/activity/*")
@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
public class ActivityServlet extends BaseServlet {

    private ActivityService activityService = new ActivityServiceImpl();
    /**
     * 添加活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityStart = request.getParameter("activityStart");
        String activityEnd = request.getParameter("activityEnd");
        Activity activity = new Activity();
        activity = packageObject(activity, request);
        activity.setActivityStart1(DateUtil.StringToDate(activityStart));
        activity.setActivityEnd1(DateUtil.StringToDate(activityEnd));
        if (activityService.addActivity(activity)) {
            writeValue(ResultInfoNew.success("添加活动成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("添加活动成功"), response);
        }
    }

    /**
     * 删除活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        if (activityService.deleteActivity(aid)) {
            writeValue(ResultInfoNew.success("删除活动成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("删除活动成功"), response);
        }
    }

    /**
     * 通过id找到活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findActivityById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        ActivityUser activityById = activityService.findActivityById(aid);
        writeValue(ResultInfoNew.success("活动信息").addOthers("activity", activityById), response);
    }

    /**
     * 查所有活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findActivitys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(ResultInfoNew.success("全部活动信息").addOthers("activitys", activityService.findActivitys()), response);
    }

    /**
     * 上传到服务器图片
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addActivityImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part aimg = request.getPart("editormd-image-file");
        System.out.println(aimg);
        if (aimg == null) {
            writeValue(ResultInfoNew.fail("请添加图片"), response);
            return;
        }
        String newFile = "\\imga\\";
        String img = FileUtil.upload(aimg, "C:\\Program Files\\Apache Software Foundation\\GreenFile"+newFile);
//        String img = FileUtil.upload(request.getPart("editormd-image-file"), "C:\\test"+newFile);
        String imgUrl = "http://106.13.113.4:8080/GreenFile"+newFile.replace("\\", "/")+img;
        writeValue(new EditorInfo(1,imgUrl,"上传成功"), response);
    }


    /**
     * 报名活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        Integer uid = 0;
        if ((uid = UserUtil.getUserId()) != null) {
            if (activityService.ifSignUp(uid, aid)) {
                activityService.SignUp(uid, aid);
                writeValue(ResultInfoNew.success("报名成功"), response);
            }else {
                writeValue(ResultInfoNew.fail("报名失败，可能存在已报名情况"), response);
            }
        }
    }


    /**
     * 是否报名活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void ifSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        Integer uid = UserUtil.getUserId();
        if (uid != null) {
            if (activityService.ifSignUp(uid, aid)) {
                writeValue(ResultInfoNew.success("未报名"), response);
            }else {
                writeValue(ResultInfoNew.fail("已报名"), response);
            }
        }else {
            writeValue(ResultInfoNew.fail("请登录"), response);
        }
    }

    /**
     * 取消活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        Integer uid = UserUtil.getUserId();
        if (uid != null) {
            if (activityService.deleteSignUp(uid, aid)) {
                writeValue(ResultInfoNew.success("取消成功"), response);
            }else {
                writeValue(ResultInfoNew.fail("取消失败"), response);
            }
        }else {
            writeValue(ResultInfoNew.fail("请登录"), response);
        }
    }

    /**
     * 查找所有报名的活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void fidnSignUpActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = UserUtil.getUserId();
        writeValue(ResultInfoNew.success("报名活动信息").addOthers("activities",
                activityService.findSignUpActivities(userId)), response);
    }

    /**
     * 返回正在进行的报名活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void fidnDuringSignUpActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = UserUtil.getUserId();
        writeValue(ResultInfoNew.success("报名活动信息").addOthers("activities",
                activityService.findSignUpDuringActivities(userId)), response);
    }

    /**
     * 返回已结束的报名的活动
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void fidnEndSignUpActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = UserUtil.getUserId();
        writeValue(ResultInfoNew.success("报名活动信息").addOthers("activities",
                activityService.findSignUpEndActivities(userId)), response);
    }
}
