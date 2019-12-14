package sign;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "16031635";
    public static final String API_KEY = "vLabU16xv764oqCIsWzOn3Gh";
    public static final String SECRET_KEY = "yGL5GM1ozGbSMQIZPIz4TprDmqz39rNT";
    //  模型BOW（词包）模型、GRNN（循环神经网络）模型、CNN（卷积神经网络）模型
    public static final String MODE = "BOW";

//    public static final String APP_ID1 = "16056207";
//    public static final String API_KEY1 = "wWgQAM1Zh7ox9ARtniHM7FPi";
//    public static final String SECRET_KEY1 = "aE3fOLtlsUTA6KGRyO5MuzbY2hLVP3Mt";

    public static void main(String[] args) {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        // 初始化一个AipB1p
        AipNlp client1 = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        String image = "D:\\dc.jpg";
        JSONObject imgTest = new Sample().img(client, image);
        System.out.println(imgTest);
        String keyword = imgTest.getString("keyword");
        try {
            String description = (String) imgTest.getJSONObject("baike_info").get("description");
            System.out.println(keyword);//类型
            System.out.println(description);//内容
        }catch (Exception e){

        }
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

//        -----------------------------------------------
        String word1 = keyword;
//        Stack stack = new Stack();
        double[] arrs;
        ArrayList<Double> doubles = new ArrayList<>();
        String[] Prams = {"纸","布料","玻璃","箱","矿泉水","瓶子","袋子","镜子","易拉罐"};
        for(String i :Prams){
//            String word2 = "电器";
//            System.out.println(i);
            JSONObject jsonObject = new Sample().sample(client1, word1, i);
//            System.out.println(jsonObject.toString(2));
            if (jsonObject.getDouble("score")>0.6){
                System.out.println("可回收垃圾");
                System.out.println(jsonObject.toString(2));
                break;
            }

        }
//        可回收主要包括废纸、塑料、玻璃、金属和布料五大类。
//        不可回收

        // 词义相似度

//        System.out.println(res);

    }
    public JSONObject img(AipImageClassify client,String image){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("baike_num", "5");

        // 参数为本地路径
//        String image = "D:\\1555643892151.jpg";
        JSONObject res = client.advancedGeneral(image, options);
        Object o = res.getJSONArray("result").get(0);
//        System.out.println(res.toString(2));
        JSONObject q = new JSONObject(o.toString());

//        System.out.println(q);
//        String keyword = q.getString("keyword");
//        System.out.println(keyword);
        return q;
    }


    public JSONObject sampleDanChi(AipNlp client,String text1,String text2) {
        JSONObject res = client.wordSimEmbedding(text1, text2, null);
        return res ;
    }
    public JSONObject sample(AipNlp client,String text1,String text2) {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", MODE);
        // 短文本相似度
        JSONObject res = client.simnet(text1, text2, options);
        return res;

    }

}
