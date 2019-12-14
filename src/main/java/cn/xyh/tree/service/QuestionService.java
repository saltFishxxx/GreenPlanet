package cn.xyh.tree.service;

import cn.xyh.tree.domain.Question;

import java.util.HashMap;
import java.util.List;

public interface QuestionService {
    boolean addQuestion(Question question);

    boolean questionCheak(Question question);

    Question queryQuestionById(int id);

    List<Question> queryQuestionAll();

    List<HashMap<String,Object>>  queryQuestionTen(String type);
}
