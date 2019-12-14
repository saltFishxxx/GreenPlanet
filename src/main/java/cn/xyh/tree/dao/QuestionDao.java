package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface QuestionDao {

    boolean addQuestion(Question question);

    boolean delQuestionById(int id);

    boolean updateQuestionById(int id, Question question);

    boolean isExist(int id);

    Question queryQuestionById(int id);

    List<Question> queryQuestionAll();

    List<Question> queryQuestionType(String type);
}
