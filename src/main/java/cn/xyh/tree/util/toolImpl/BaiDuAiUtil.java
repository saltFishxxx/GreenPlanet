package cn.xyh.tree.util.toolImpl;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiDuAiUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "16031635";
    public static final String API_KEY = "vLabU16xv764oqCIsWzOn3Gh";
    public static final String SECRET_KEY = "yGL5GM1ozGbSMQIZPIz4TprDmqz39rNT";
    //  模型BOW（词包）模型、GRNN（循环神经网络）模型、CNN（卷积神经网络）模型
    public static final String MODE = "BOW";

    public JSONObject img(AipImageClassify client, String image){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("baike_num", "5");
        // 参数为本地路径
        JSONObject res = client.advancedGeneral(image, options);
        Object o = res.getJSONArray("result").get(0);
        JSONObject q = new JSONObject(o.toString());

        return q;
    }
    public JSONObject sampleDanChi(AipNlp client, String text1, String text2) {
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
