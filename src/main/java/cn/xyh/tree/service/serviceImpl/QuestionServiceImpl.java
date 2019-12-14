package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.QuestionDao;
import cn.xyh.tree.dao.daoImpl.QuestionDaoImpl;
import cn.xyh.tree.domain.Question;
import cn.xyh.tree.service.QuestionService;
import org.junit.jupiter.api.Test;

import java.util.*;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao = new QuestionDaoImpl();

    @Override
    public boolean addQuestion(Question question){
        return questionDao.addQuestion(question);
    }

//    public boolean delQuestionById(int id){
//
//    }

//    public boolean updateQuestionById(int id, Question question){
//
//    }

    /**
     * 检查答案是否正确
     * @param question
     * @return
     */
    @Override
    public boolean questionCheak(Question question){
        return questionDao.queryQuestionById(question.getQuestion_id()).getQuestion_right()
                .equals(question.getQuestion_right()) ? true : false;
    }
    @Override
    public Question queryQuestionById(int id){
        //检测是否存在问题，存在返回 问题集，不存在NULL
        return questionDao.isExist(id) == true ? questionDao.queryQuestionById(id) : null;
    }

    /**
     * 查询10道随机题
     * @return
     */
    @Override
    public List<Question> queryQuestionAll(){
        List<Question> questionsList = new ArrayList<>();
        HashSet<Question> questionsSet = new HashSet<>();
//        System.out.println(questionDao.queryQuestionAll().toString());
        while (true){
            int random = new Random().nextInt(questionDao.queryQuestionAll().size());
//           此处可能存在逻辑错误
            questionsSet.add(questionDao.queryQuestionAll().get(random));
            if(questionsSet.size()>10){
                break;
            }
        }
        Iterator iterator =   questionsSet.iterator();
        while(iterator.hasNext()){
            questionsList.add((Question) iterator.next());
//            if(questionsList.size()>10){break;}
        }
        return questionsList;
    }

    @Override
    public List<HashMap<String,Object>>  queryQuestionTen(String type){
        ArrayList<HashMap<String,Object>> arrayQuestionList = new ArrayList<>();
        for (Question q : questionDao.queryQuestionType(type)) {
            HashMap<String , Object> questionMap = new HashMap<>();
            String[] split = q.getQuestion_descri().replace('?','-').split("-");
            //存入id
            questionMap.put("id", q.getQuestion_id());
            //存入选项数据
            ArrayList<String> questionList = new ArrayList<>();
            questionList .addAll(Arrays.asList(split[1].split(";")));
            questionMap.put("content", questionList);
            //存入标题数据
            questionMap.put("name",split[0]);
            //存入答案
            questionMap.put("answer",q.getQuestion_right());
            //放入arrayQuestionList中
            arrayQuestionList.add(questionMap);
        }

        return arrayQuestionList;
    }

}
