package cn.xyh.tree.web.servlet;


import cn.xyh.tree.domain.Question;
import cn.xyh.tree.domain.ResultInfo;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.QuestionService;
import cn.xyh.tree.service.serviceImpl.QuestionServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/question/*")
public class QuestionServlet extends BaseServlet{
    QuestionService questionService = new QuestionServiceImpl();

    public void addQuestion(HttpServletRequest Request, HttpServletResponse Response){

    }
    public void updataQuestion(HttpServletRequest Request, HttpServletResponse Response){

    }
//    验证题目是否错误
    public void cheakQuestion(HttpServletRequest request , HttpServletResponse response){
        Question question = new Question() ;

        //封装表单数据
        Question packageQuestion = packageObject(question, request);
        Boolean cheakQuestion = questionService.questionCheak(packageQuestion);

       if(cheakQuestion){
            //  true 答案正确 增加能量
           writeValue(new ResultInfo("恭喜你答对了",1),response);
       }else{
           writeValue(new ResultInfo("回答错误",0),response);
       }
    }
    //查询所有题目
//    public void queryQuestionAll(HttpServletRequest Request, HttpServletResponse Response){
//        List<Question> questionsAll = questionService.queryQuestionAll();
//        //转换JSON
//        JSONArray jsonQuestionArray = new JSONArray();
//        jsonQuestionArray.add(questionsAll);
//        //存储JSON格式
//        HashMap<String,Object> questionMap = new HashMap<>();
//        questionMap.put("question",jsonQuestionArray);
//        //是否存在登录态
//        if(Request.getSession().getAttribute("user")!=null){
//            writeValue(new ResultInfo("获取成功", 1, questionMap), Response);
//        }else{
//            writeValue(new ResultInfo("获取失败，请查看是否登录",0),Response);
//        }
//        System.out.println(jsonQuestionArray.toString());
//    }
    //根据标签查看题目
    public void queryQuestionType(HttpServletRequest Request, HttpServletResponse Response){
        String type = "选";
//        String type1 = type.substring(1,2);
        //是否存在登录态
//        Request.getSession().getAttribute("user")!=null;
        List<HashMap<String, Object>> queryMap = questionService.queryQuestionTen(type);
            //转换JSON
        if(queryMap!=null){
            JSONArray jsonQuestionMap = new JSONArray();
            jsonQuestionMap.add(queryMap);
            //存储JSON格式
            HashMap<String,Object> questionMap = new HashMap<>();
            questionMap.put("question",jsonQuestionMap);
            //send
            writeValue(new ResultInfo("获取成功", 1, questionMap), Response);
        }else{
            writeValue(new ResultInfo("获取失败，请查看是否登录",0),Response);
        }

    }


}
