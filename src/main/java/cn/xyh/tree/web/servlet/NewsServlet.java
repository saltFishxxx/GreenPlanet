package cn.xyh.tree.web.servlet;

import cn.xyh.tree.dao.NewsDao;
import cn.xyh.tree.domain.*;
import cn.xyh.tree.service.GoodService;
import cn.xyh.tree.service.NewsService;
import cn.xyh.tree.service.UserService;
import cn.xyh.tree.service.serviceImpl.GoodServiceImpl;
import cn.xyh.tree.service.serviceImpl.NewsServiceImpl;
import cn.xyh.tree.util.toolImpl.ArrayUtil;
import cn.xyh.tree.util.toolImpl.JedisUtil;
import cn.xyh.tree.util.toolImpl.SerializableUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/news/*")
public class NewsServlet extends BaseServlet {
    private NewsService newsService = new NewsServiceImpl();
    private GoodService goodService = new GoodServiceImpl();

    //添加新闻和所有图片的id
    public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有图片的id
        String[] imgs = request.getParameterValues("imgs[]");
        System.out.println(imgs);
        News1 news1 = new News1();
        news1.setNewsLittletype(request.getParameter("newsLittletype"));
        news1.setNewsType(request.getParameter("newsType"));
        news1.setNewsContent(request.getParameter("newsContent"));
        news1.setNewsName(request.getParameter("newsName"));
//        news1 = packageObject(news1, request);
        if (news1.getNewsType() == null || "".equals(news1.getNewsType())) {
            news1.setNewsType("无");
        }
        if (news1.getNewsLittletype() == null || "".equals(news1.getNewsLittletype())) {
            news1.setNewsLittletype("无");
        }
        List<NewsImg> newsImgs = new ArrayList<>();
        for(int i = 0; i < imgs.length; i++){
            NewsImg newsImg = new NewsImg();
            newsImg.setNewsImgId(Integer.parseInt(imgs[i]));
            newsImgs.add(newsImg);
        }
        news1.setNewsImgs(newsImgs);
        news1.setNewsDatetime(new Date());
        if (newsService.addNews(news1)) {
            writeValue(new ResultInfo("添加新闻成功", 1), response);
        }else {
            writeValue(new ResultInfo("添加新闻失败", 0), response);
        }
    }

    //查看新闻根据类别或全部的
    public void findNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        writeValue(newsService.findNews(category), response);
    }

    //根据id查看新闻
    public void findNewsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nid = request.getParameter("nid");
        News1 news = newsService.findNewsById(nid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (news!=null) {
            writeValue(ResultInfoNew.success("查到文章").addOthers("news", news).addOthers("newsDate", simpleDateFormat.format(news.getNewsDatetime())), response);
        }else {
            writeValue(ResultInfoNew.success("查无此文章").addOthers("news", news), response);
        }
    }

    //根据用户id查收藏的文章
    public void findGoodNewsByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        List<News1> gooNewsByUid = goodService.findGooNewsByUid(uid);
        writeValue(ResultInfoNew.success("返回成功").addOthers("news", gooNewsByUid), response);
    }

    //速文推荐
    public void findQuikNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] nids = request.getParameterValues("nids[]");
        //将字符串数组转换为整形数组
        List<News1> quikNews = newsService.findQuikNews();
        if (quikNews!= null) {
            writeValue(ResultInfoNew.success("推荐成功"), response);
        }else {
            writeValue(ResultInfoNew.success("推荐失败"), response);
        }
    }


    //根据新闻id删除
    public void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nid = request.getParameter("nid");
        if (newsService.deleteNews(nid)) {
            writeValue(ResultInfoNew.success("删除成功"),response);
        }else {
            writeValue(ResultInfoNew.fail("删除失败"),response);
        }
    }

    //更新新闻
    public void updateNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News1 news1 = new News1();
        news1 = packageObject(news1, request);
        if (newsService.updateNews(news1)) {
            writeValue(ResultInfoNew.success("更新成功"),response);
        }else {
            writeValue(ResultInfoNew.fail("更新失败"),response);
        }
    }

    //速文推荐
    public void quickNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News1> quikNews = newsService.findQuikNews();
        writeValue(ResultInfoNew.success("速文推荐").addOthers("quickNews", quikNews), response);
    }
}
