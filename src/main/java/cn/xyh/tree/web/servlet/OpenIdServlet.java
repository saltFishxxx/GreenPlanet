package cn.xyh.tree.web.servlet;


import cn.xyh.tree.domain.ResultInfo;
import cn.xyh.tree.util.toolImpl.GetUrlUtil;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/web/*")
public class OpenIdServlet extends BaseServlet {
        private static final String APPID = "wx672b8174ac59366b";
        private static final String SECRET = "37bc2c3cfe5c9f32f5a195efcb7874c9";
//    String js_code = request.getParameter("js_code");
        private static final String grant_type = "authorization_code";
        private static final String root = "https://api.weixin.qq.com/sns/jscode2session?";

        public void getOpenID(HttpServletRequest request , HttpServletResponse response) throws IOException {
            String js_code = request.getParameter("js_code");
            GetUrlUtil j = new GetUrlUtil();
            String url = root + "appid=" + APPID +"&secret="+SECRET+"&js_code="+js_code+"&grant_type="+grant_type ;
            //	System.out.println(url);
            String json = j.loadJson(url);
            JSONObject jsonObject = JSONObject.fromObject(json);
            HashMap<String,Object> hashMapJson = new HashMap<>();
            hashMapJson.put("openid",jsonObject);

            writeValue(new ResultInfo("获取成功", 1,hashMapJson), response);

//            System.out.println(jsonObject);
            response.getWriter().write(json);
        }

}
