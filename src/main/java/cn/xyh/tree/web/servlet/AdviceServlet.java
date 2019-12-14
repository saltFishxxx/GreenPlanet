package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.AdviceService;
import cn.xyh.tree.service.serviceImpl.AdivceServiceImpl;
import cn.xyh.tree.util.toolImpl.JedisUtil;
import cn.xyh.tree.util.toolImpl.SerializableUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/advice/*")
public class AdviceServlet extends BaseServlet {
    private AdviceService adviceService = new AdivceServiceImpl();

    //建议上传
    public void addAdvice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        if (adviceService.addAdvice(content,28)){
            writeValue(ResultInfoNew.success("上传成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("上传失败"), response);
        }

    }
}
