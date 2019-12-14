package cn.xyh.tree.web.servlet;

import cn.xyh.tree.domain.Card;
import cn.xyh.tree.domain.ResultInfo;
import cn.xyh.tree.domain.ResultInfoNew;
import cn.xyh.tree.service.UserService;
import cn.xyh.tree.service.serviceImpl.UserServiceImpl;
import cn.xyh.tree.util.toolImpl.*;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.nlp.AipNlp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@WebServlet("/util/*")
public class UtilServlet extends BaseServlet {

    private Jedis jedis = JedisUtil.getJedis();
    private static final Logger log = Logger.getLogger(UtilServlet.class);
    private UserService userService = new UserServiceImpl();

    //修改密码的电话号码
    public void sendMessgeUpdate(HttpServletRequest Request, HttpServletResponse Response) {
        SendMessgeUtil send = new SendMessgeUtil();
        String tel = Request.getParameter("tel");
        int authCode = new RandomUtil().randomMessge();
        //当前的短信验证码
        log.info("当前的短信验证码是：" + authCode);
        String code = "#code#=" + authCode; //7随机7位

        //验证用户电话和填写电话是否一样
        if (userService.checkTel(tel)) {
            //发送
            send.sendMessge(tel, code);
            //存入session
            Request.getSession().setAttribute("authCode", authCode);

            writeValue(new ResultInfo("发送成功", 1), Response);
        } else {
            writeValue(new ResultInfo("发送失败，电话号码有误", 0), Response);
            log.info("发送失败，电话号码有误");
        }
    }

    //注册发送的电话号码
    public void sendMessge(HttpServletRequest Request, HttpServletResponse Response) {
        SendMessgeUtil send = new SendMessgeUtil();
        String tel = Request.getParameter("tel");
        int authCode = new RandomUtil().randomMessge();
        //当前的短信验证码
//        log.info("当前的短信验证码是：" + authCode);
        String code = "#code#=" + authCode; //7随机7位
        //发送
        send.sendMessge(tel, code);
        System.out.println(authCode);
        //存入session
        Request.getSession().setAttribute("authCode", authCode);

        jedis.set("authCode", authCode + "");
        writeValue(new ResultInfo("发送成功", 1), Response);

    }
    //图片上传
    public void baiDuAi(HttpServletRequest request,HttpServletResponse response){
//        HashMap<String,Object> map = new HashMap<>();
        Card card = new Card();
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(BaiDuAiUtil.APP_ID,BaiDuAiUtil.API_KEY, BaiDuAiUtil.SECRET_KEY);
        // 初始化一个AipB1p
        AipNlp client1 = new AipNlp(BaiDuAiUtil.APP_ID,BaiDuAiUtil.API_KEY,BaiDuAiUtil.SECRET_KEY);
//        String image = "D:\\dc.jpg";
        //-------------------------------------
//        String newFile = "\\img\\";
//        String img = null;
//        String imgUrl = null ;
//        try {
//                img = FileUtil.upload(request.getPart("editormd-image-file"), "C:\\Program Files\\Apache Software Foundation\\GreenFile"+newFile);
//                imgUrl = "http://106.13.113.4:8080/GreenFile"+newFile.replace("\\", "/")+img;
//                System.out.println(imgUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if(isMultipart){
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                Iterator<FileItem> iterator = fileItems.iterator();
                while(iterator.hasNext()){
                    FileItem next = iterator.next();
                    //不是普通表单字段
                    if(!next.isFormField()){
                        String flieName = next.getName();
//                        String path = "http://106.13.113.4:8080/GreenFile/img";
                        String path = "C:\\Program Files\\Apache Software Foundation\\GreenFile\\img";
//                        String path ="E:\\upload";
                        File file = new File(path,flieName);
                        next.write(file);

                        String image = path+"/"+flieName;
                        System.out.println(image);
                        //------------------------------------
                        JSONObject imgTest = new BaiDuAiUtil().img(client, image);
                        System.out.println(imgTest);
                        String keyword = imgTest.getString("keyword");
                        String root  = imgTest.getString("root");
                        String description = "瓶子（bottle），容器，一般口较小，颈细肚大，多用塑料、瓷或玻璃制成。常用于装液体物质（例如：水，油等）。\n                                             ";
                        try{
                             description = (String) imgTest.getJSONObject("baike_info").get("description");
                        }catch (Exception e){}
                            card.setCardName(keyword);//类型名字
                            card.setCardContent(description);//保存内容
                            card.setCardSpecies(root);

//        -----------------------------------------------
                        String word1 = keyword;
                        ArrayList<Double> doubles = new ArrayList<>();
                        String[] pramsK = {"纸","布料","玻璃","箱","矿泉水","瓶子","袋子","镜子","易拉罐"};
                        card.setCardType("不可回收垃圾");
                        for(String i :pramsK){
                            JSONObject jsonObject = new BaiDuAiUtil().sample(client1, word1, i);
                            if (jsonObject.getDouble("score")>0.6){
                                card.setCardType("可回收垃圾");
                                break;
                            }
                        }

                        HashMap<String,Object> map = new HashMap<>();

                        net.sf.json.JSONObject jsonObject22 = net.sf.json.JSONObject.fromObject(card);
                        System.out.println(jsonObject22);
                        map.put("card",jsonObject22);
                        writeValue(new ResultInfo("获取成功",1,map),response);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }catch (Exception e) {
            writeValue(new ResultInfo("获取失败",0),response);
            e.printStackTrace();
        }

    }
}
