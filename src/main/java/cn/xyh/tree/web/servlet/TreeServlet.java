package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.ResultInfo;
import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.TreeService;
import cn.xyh.tree.service.serviceImpl.TreeServiceImpl;
import cn.xyh.tree.util.toolImpl.JedisUtil;
import cn.xyh.tree.util.toolImpl.SerializableUtil;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/tree/*")
public class TreeServlet extends BaseServlet {
    private TreeService treeService = new TreeServiceImpl();
    private Jedis jedis = JedisUtil.getJedis();

    public void queryTree(HttpServletRequest request,HttpServletResponse response){
        HashMap treeMap = new HashMap();
        if(jedis.get("user".getBytes())!=null){
            //取出redis中的session值 writeValueAsString
            byte[] bytes = jedis.get("user".getBytes());
            User user = (User) SerializableUtil.unserizlize(bytes);
            Tree treeT = treeService.queryTreeById(user);
            if(treeT!=null) {
                JSONObject jsonObject = JSONObject.fromObject(treeService.queryTreeById(user));
                treeMap.put("treeMap", jsonObject);
                writeValue(new ResultInfo("获取信息成功", 1, treeMap), response);
            }else{
                writeValue(new ResultInfo("没有领养小树", 0), response);
            }
        }else {
            writeValue(new ResultInfo("没有登录，请登录", 0), response);
        }
    }

    public void addPoint(HttpServletRequest request,HttpServletResponse response){
        HashMap treeMap = new HashMap();
        if(jedis.get("user".getBytes())!=null){
            int point = Integer.parseInt(request.getParameter("point"));

            byte[] bytes = jedis.get("user".getBytes());
            User user = (User) SerializableUtil.unserizlize(bytes);

            boolean flag = treeService.addEnergy(user, point);
            JSONObject jsonObject = JSONObject.fromObject(treeService.queryTreeById(user));
            treeMap.put("flag",flag);
            treeMap.put("treeMap",jsonObject);
            writeValue(new ResultInfo("能量添加成功", 1,treeMap), response);
        }else {
            writeValue(new ResultInfo("没有登录，请登录", 0), response);
        }
    }

    public void addTree(HttpServletRequest request,HttpServletResponse response){
        if(jedis.get("user".getBytes())!=null){
            Tree tree = new Tree();
            byte[] bytes = jedis.get("user".getBytes());
            User user = (User) SerializableUtil.unserizlize(bytes);
            boolean flag = treeService.addTree(tree, user);
            writeValue(ResultInfoNew.success("获取小树").addOthers("flag",flag),response);
        }
    }
}
