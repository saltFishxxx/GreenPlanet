package cn.xyh.tree.web.servlet;

import cn.xyh.tree.dao.EverydayDao;
import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.service.EveryService;
import cn.xyh.tree.service.serviceImpl.EveryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/everyday/*")
public class EverydayServlet extends BaseServlet {
    private EveryService everyService = new EveryServiceImpl();

    public void sendSentence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(ResultInfoNew.success("每日一句").addOthers("sentence", everyService.findEveryDay()), response);
    }
}
