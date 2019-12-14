package cn.xyh.tree.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 *  其他servlet通过调用该类，实现统一管理servlet
 */
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求资源路径
        String requestURI = req.getRequestURI();
        //获取方法名
        String substring = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        //使用反射调用方法
        try {
            Method method = this.getClass().getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将转换的json数据返回到页面的输出流
    public void writeValue(Object object, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(response.getOutputStream(), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将java对象转换为json
    public String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    //封装用户
    public <T> T packageObject(T object, HttpServletRequest request) {
        try {
            BeanUtils.populate(object, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
