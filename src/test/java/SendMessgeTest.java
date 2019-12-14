import cn.xyh.tree.util.toolImpl.RandomUtil;
import cn.xyh.tree.util.toolImpl.SendMessgeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.UrlResource;
import sign.Sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SendMessgeTest {


    @Test
    public void send(){
        SendMessgeUtil send = new SendMessgeUtil();
        String tel = "18983592583";  //电话号码
        int number = new RandomUtil().randomMessge(); //随机7位
        String code = "#code#="+number; //7位
        send.sendMessge(tel,code);
        
    }
    @Test
     public void img(){
        String url ="https://recognition.image.myqcloud.com/v1/detection/imagetag_detect";
        String appid = "1254284740";
        String secretId ="AKIDjFRUKpWAcfVYQ7AYnvjTBKDtkOsIAehc";
        String secretKey = "43ua8SXs53EI7i8aopK2Xp5Vr79xa1ED";
        String bucketName = "xiao-1254284740";
        String result = null ;
        PrintWriter out = null ;
        BufferedReader in = null;
//        result =;
        String imgUrl = "";
         try {
             URL img_url = new URL(url);
             URLConnection urlConnection = img_url.openConnection();
             // 设置通用的请求属性

             urlConnection.setRequestProperty("Accept", "application/json");
             urlConnection.setRequestProperty("connection", "Keep-Alive");
             urlConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("host","recognition.image.myqcloud.com");
            urlConnection.setRequestProperty("authorization",Sign.appSign(1254284740,secretId,secretKey,bucketName,System.currentTimeMillis()+60));
             System.out.println(Sign.appSign(1254284740,secretId,secretKey,bucketName,7776000));
             //             允许POST请求
             urlConnection.setDoInput(true);
             urlConnection.setDoOutput(true);
//          获取输出流
             out = new PrintWriter(urlConnection.getOutputStream());
             out.print(appid);
//             out.print
             // flush输出流的缓冲
             out.flush();
             in = new BufferedReader(
                     new InputStreamReader(urlConnection.getInputStream()));
             String line ;
             while ((line = in.readLine()) != null) {
                 result += line;
             }

         } catch (MalformedURLException e) {
             e.printStackTrace();
         }catch (Exception e) {
             e.printStackTrace();
         }finally{
             try{
                 if(out!=null){
                     out.close();
                 }
                 if(in!=null){
                     in.close();
                 }
             }
             catch(IOException ex){
                 ex.printStackTrace();
             }
         }
     }

}
