package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.*;
import cn.xyh.tree.service.NewsImgService;
import cn.xyh.tree.service.serviceImpl.NewsImgServiceImpl;
import cn.xyh.tree.util.toolImpl.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * web3.0以上不需要导入文件上传的jar包
 */
@WebServlet("/file/*")
@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
public class UploadServlet extends BaseServlet {
    private NewsImgService newsImgService = new NewsImgServiceImpl();

    //上传一个
    public void uploadOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newFile = "\\img\\";
        String img = FileUtil.upload(request.getPart("editormd-image-file"), "C:\\Program Files\\Apache Software Foundation\\GreenFile"+newFile);
//        String img = FileUtil.upload(request.getPart("editormd-image-file"), "C:\\test"+newFile);
        String imgUrl = "http://106.13.113.4:8080/GreenFile"+newFile.replace("\\", "/")+img;
        System.out.println(imgUrl);
        NewsImg newsImg = new NewsImg();

        //封装newsImg
        newsImg.setNewsImgUrl(imgUrl);
        int upload = newsImgService.upload(newsImg);
        newsImg.setNewsImgId(upload);
        writeValue(new EditorInfo(1,imgUrl,""+upload), response);
    }

    //上传多个
    public void uploadMany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileUtil.uploadMany(request.getParts(), "C:\\Program Files\\Apache Software Foundation\\GreenFile\\img");
    }
}
