package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.*;
import cn.xyh.tree.service.GoodService;
import cn.xyh.tree.service.UserService;
import cn.xyh.tree.service.serviceImpl.GoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/good/*")
public class GoodServlet extends BaseServlet {
    private GoodService goodService = new GoodServiceImpl();

    //添加收藏或点赞
    public void addGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Good good = packae(request);
        if (goodService.addGood(good)) {
            writeValue(ResultInfoNew.success("添加成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("添加失败，可能存在已添加情况"), response);
        }
    }

    //判断是否收藏或点赞
    public void ifGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Good good = packae(request);
        if (goodService.ifGood(good)) {
            if (good.getGoodType() == 1) {
                writeValue(ResultInfoNew.success("未点赞"), response);
            }else if (good.getGoodType() == 2){
                writeValue(ResultInfoNew.success("未收藏"), response);
            }
        }else {
            if (good.getGoodType() == 1) {
                writeValue(ResultInfoNew.fail("已点赞"), response);
            }else {
                writeValue(ResultInfoNew.fail("已收藏"), response);
            }
        }
    }

    //收藏或点赞的个数
    public void goodCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(ResultInfoNew.success("点赞数返回成功").addOthers("点赞数", goodService.goodCount(packae(request))), response);
    }

    //取消点赞或收藏
    public void deleteGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Good good = packae(request);
        if (goodService.deleteGood(good)) {
            writeValue(ResultInfoNew.success("取消成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("取消失败"), response);
        }
    }

    //封装用户的信息
    public Good  packae(HttpServletRequest request) {
        User user = new User();
        News1 news1 = new News1();
        Good good = new Good();
        good = packageObject(good, request);
        news1 = packageObject(news1, request);
        user = packageObject(user, request);
        good.setNews(news1);
        good.setUser(user);
        return good;
    }
}
