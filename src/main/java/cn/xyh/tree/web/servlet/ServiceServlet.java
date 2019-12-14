package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.domain.Service;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.ServiceService;
import cn.xyh.tree.service.serviceImpl.ServiceServiceImpl;
import cn.xyh.tree.util.toolImpl.UserUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

/**
 * @author xyh
 */
@WebServlet("/service/*")
public class ServiceServlet extends BaseServlet {
    private ServiceService serviceService = new ServiceServiceImpl();

    /**
     * 查询用户的服务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findServicesByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        Integer nStatus = null;
        if (status != null && !"".equals(status)) {
            nStatus = Integer.parseInt(status);
        }
        Integer userId = UserUtil.getUserId();
        writeValue(ResultInfoNew.success("服务信息").addOthers("services",
                serviceService.findByUid(userId, nStatus)), response);
    }

    /**
     * 申请服务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void insertService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = new Service();
        service = packageObject(service, request);
        User user = new User();
        user.setUser_id(UserUtil.getUserId());
        service.setUser(user);
        if (serviceService.inserService(service)) {
            writeValue(ResultInfoNew.success("申请成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("申请失败"), response);
        }
    }

    public void pass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        if (serviceService.updateService(sid, 1)) {
            writeValue(ResultInfoNew.success("审核通过成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("审核通过失败"), response);
        }
    }

    public void notPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        if (serviceService.updateService(sid, 2)) {
            writeValue(ResultInfoNew.success("审核拒绝成功"), response);
        }else {
            writeValue(ResultInfoNew.fail("审核拒绝失败"), response);
        }
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(ResultInfoNew.success("所有用户的服务").addOthers("services", serviceService.findAll()), response);
    }
}
