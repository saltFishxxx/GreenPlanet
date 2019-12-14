
import cn.xyh.tree.domain.Question;
import cn.xyh.tree.service.QuestionService;
import cn.xyh.tree.service.serviceImpl.QuestionServiceImpl;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class QuestionDaoTest {
    QuestionService questionService = new QuestionServiceImpl();

    @Test
    public void addQuestion(){
//        Question question = new Question(
//                "单选题",
//                "如果一个地区的()元素分布异常，可引起地方性甲状腺肿或克汀病?铁;硒;碘;钙",
//                "C",
//                10);
//
//        System.out.println(questionService.addQuestion(question));//true
    }
    @Test
    public void queryQuestionById(){
        int id =  3 ;

//        System.out.println(questionService.queryQuestionById(id).toString());
    }

    @Test
    public void queryQuestionAll(){

        String type = "选";
//        System.out.println(type.substring(type.length()-3,type.length()-1));
        List<HashMap<String, Object>> queryMap = questionService.queryQuestionTen(type);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(queryMap);
        System.out.println(jsonArray.toString());
    }

    @Test
    public void cheakQuestion(){
        Question question = new Question();
        question.setQuestion_id(5);
        question.setQuestion_right("A");
        if (questionService.questionCheak(question)){
            System.out.println("答案正确");
        }else{
            System.out.println("答案错误");
        }
    }

}
