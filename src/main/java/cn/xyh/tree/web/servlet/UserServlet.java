package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.ResultInfo;
import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.TreeService;
import cn.xyh.tree.service.UserService;
import cn.xyh.tree.service.serviceImpl.TreeServiceImpl;
import cn.xyh.tree.service.serviceImpl.UserServiceImpl;
import cn.xyh.tree.util.toolImpl.JedisUtil;
import cn.xyh.tree.util.toolImpl.SerializableUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    private Jedis jedis = JedisUtil.getJedis();

    //登陆
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        Map<String ,Object> map =  new HashMap<>();

        //封装表单数据
        User packageUser = packageObject(user, request);
        User sqlUser = userService.login(packageUser);
        JSONObject userJson = JSONObject.fromObject(sqlUser);
        map.put("userInform",userJson);

        if (sqlUser != null) {
            //登陆成功，在session中存储登陆状态
            request.getSession().setAttribute("user", sqlUser);

            //登陆成功保存在redis
            if (jedis.get("user".getBytes())==null){
                jedis.set("user".getBytes(), SerializableUtil.serialize(sqlUser));

//                byte[] bytes = jedis.get("user".getBytes());
//                Object object = SerializableUtil.unserizlize(bytes);
//                System.out.println(object.toString());
                //设置过期时间
//                jedis.expire("user", 60*30);
            }
            writeValue(new ResultInfo("登陆成功", 1,map), response);
        }else {
            writeValue(new ResultInfo("账号或密码错误", 0), response);
        }
    }

    //退出登陆状态
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getSession().invalidate();
        jedis.del("user");
        writeValue(new ResultInfo("退出成功", 1), response);
    }

    //判断用户是否处于登陆状态
    public void checkStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (jedis.get("user")!=null) {
            writeValue(new ResultInfo("已登陆", 1), response);
        }else {
            writeValue(new ResultInfo("未登陆", 0), response);
        }
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        User packageUser = packageObject(user, request);
        //存入openid

        String userCode = request.getParameter("authCode");

        if (jedis.get("authCode").equals(userCode)) {
            boolean register = userService.register(packageUser);
            if(register==true){
                jedis.del("authCode");
                writeValue(new ResultInfo("注册成功", 1), response);
            }else{
//                jedis.del("authCode");
                writeValue(new ResultInfo("用户或手机存在", 2), response);
            }
        }else {
            writeValue(new ResultInfo("验证码错误", 0), response);
        }
        writeValue(new ResultInfo("未知错误", 0), response);

    }

    //更新密码
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        User packageUser = packageObject(user, request);
        if(jedis.get("flag").equals("true")){
            if (userService.updatePassword(packageUser)) {
                jedis.del("flag");
                writeValue(new ResultInfo("更新密码成功", 1), response);
            }else {
                writeValue(new ResultInfo("更新密码失败", 0), response);
            }
        }else {
            writeValue(new ResultInfo("电话号码验证失败", 0), response);
        }

    }

    /**
     * 检查用户名是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkUname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        if (userService.checkUname(uname)) {
            writeValue(new ResultInfo("用户名存在", 1), response);
        }else {
            writeValue(new ResultInfo("用户名不存在", 0), response);
        }
    }
    public void updateInfo(HttpServletRequest request,HttpServletResponse response){
        String telphone = request.getParameter("tel");
        String userCode = request.getParameter("authCode");
        if(jedis.get("authCode")!=null){
            if(jedis.get("authCode").equals(userCode)){
                //匹配成功
                if(telphone!=null){
                    if(userService.checkTel(telphone)){
                        //true 和false
                        //true 验证成功
                        User user = userService.queById(telphone);
                        jedis.set("flag","true");
                        writeValue(ResultInfoNew.success("验证成功").addOthers("userInform",user),response);
//                        writeValue(new ResultInfo("验证成功",1),response);
                    }else{
                        writeValue(ResultInfoNew.fail("电话号码错误"),response);
                    }
                }else{
                    writeValue(new ResultInfo("电话号码空",500),response);
                }
            }else{
                writeValue(ResultInfoNew.fail("验证码错误"),response);
            }
        }else {
            writeValue(new ResultInfo("验证码丢失",501),response);
        }
    }
    public void userRankingList(HttpServletRequest request , HttpServletResponse response){
        TreeService treeService = new TreeServiceImpl();
        JSONArray array  = new JSONArray();
        List<User> users = userService.userRankingList();
        List<HashMap<String,Object>> userRankingList = new ArrayList<>();
        if(jedis.get("user".getBytes())!=null){
            byte[] bytes = jedis.get("user".getBytes());
            User user = (User) SerializableUtil.unserizlize(bytes);
            for (User u : users){
                Tree tree = treeService.queryTreeById(u);
//            tree.getTree_power();
                HashMap<String, Object> map = new HashMap<>();
                map.put("power",tree.getTree_power());
                map.put("openid",u.getUser_code());
                map.put("user_imgs",u.getUser_imgs());
                map.put("user_city",u.getUser_city());
                map.put("user_nickname",u.getUser_nickname());
                userRankingList.add(map);
            }
            array.add(userRankingList);
//        JSONObject array1  = JSONObject.fromObject(array);
//        writeValue(new ResultInfo("查询成功","1",),response);
            try {
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("rankingList",array);
                map2.put("status",1);
                map2.put("message","查询成功");
                map2.put("rank",userService.userRankById(user));
                JSONObject jsonObject = JSONObject.fromObject(map2);
                response.getWriter().print(jsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            writeValue(ResultInfoNew.fail("尚未登录"),response);
        }

//        writeValue(ResultInfoNew.success("查询成功").addOthers("rankingList",array.toString()),response);
    }
}
