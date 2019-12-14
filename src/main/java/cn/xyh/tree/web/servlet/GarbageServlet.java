package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.Garbage;
import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.service.GarbageService;
import cn.xyh.tree.service.serviceImpl.GarbageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/garbage/*")
public class GarbageServlet extends BaseServlet {
    private GarbageService garbageService = new GarbageServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(ResultInfoNew.success("垃圾箱全部位置").addOthers("garbages",
                garbageService.findAllGarbages()), response);
    }

    public void findNearGarbage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Garbage garbage = new Garbage();
        garbage = packageObject(garbage, request);
        writeValue(ResultInfoNew.success("最近的垃圾桶").addOthers("nearGarbage",
                garbageService.findNearGarbage(garbage)), response);
    }
}
