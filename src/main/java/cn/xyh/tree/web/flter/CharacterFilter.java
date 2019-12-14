package cn.xyh.tree.web.flter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  拦截器
 */

@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //判断请求方式，设置请求编码
        //使用tomcat8之前版本需要单独处理中文乱码
        if ("post".equalsIgnoreCase(request.getMethod())) {
            request.setCharacterEncoding("utf-8");
        }
        //设置响应编码
        response.setContentType("text/html;charset=utf-8");
        //跨域请求
        response.setHeader("Access-Control-Allow-Origin","*");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
