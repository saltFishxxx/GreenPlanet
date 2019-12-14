package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.QuestionDao;
import cn.xyh.tree.domain.Question;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    //日志
    private static final Logger       log          = Logger.getLogger(UserDaoImpl.class);
    //使用了jdbcTemplate
    private              JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * 增加问题
     */
    @Override
    public boolean addQuestion(Question question) {
        String sql = "INSERT INTO gp_question(question_type,question_descri,question_right,question_power) VALUES(?,?,?,?) ";
        boolean flag = false;
        int update = jdbcTemplate.update(sql,
                    question.getQuestion_type(),question.getQuestion_descri(),question.getQuestion_right(),question.getQuestion_power());
//            sqlUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Question>(Question.class),);
        if(update > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据id删除问题
     */
    @Override
    public boolean delQuestionById(int id) {
        String sql = "DELETE FROM gp_question where question_id = ?";
        boolean flag = false;
        try {
            int update = jdbcTemplate.update(sql,id);
            if(update > 0) {
                flag = true;
            }
        }
        catch (DataAccessException e) {

        }
        return flag;
    }
    /**
     * 根据id更正问题
     */
    @Override
    public boolean updateQuestionById(int id, Question question) {
        String sql = "UPDATE gp_question set question_type =? ,question_descri =? ,question_right =?,question_power = ? WHERE question_id = ?";
        boolean flag = false;
        try {
            int update = jdbcTemplate.update(sql,question.getQuestion_type(),question.getQuestion_descri(),question.getQuestion_right(),question.getQuestion_power(),id);
            if(update > 0) {
                flag = true;
            }
        }
        catch (DataAccessException e) {

        }
        return flag;
    }
    /**
     * 查询问题是否存在
     */
    @Override
    public boolean isExist(int id){
        return queryQuestionById(id) != null ? true : false ;
    }
    /**
     * 根据问题id查询问题
     */
    @Override
     public Question queryQuestionById(int id){
        String sql = "SELECT * FROM gp_question WHERE question_id = ?" ;
         Question question = null ;
         try{
//             sqlUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Question>(Question.class),);
              question = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Question>(Question.class),id);
         }catch(DataAccessException e){

         }
         return question;
     }
    /**
     * 查询所有问题
     */
    @Override
    public List<Question> queryQuestionAll(){
        String sql = "SELECT * FROM gp_question ";
        ArrayList<Question> questionArrayList = null ;
        try{
          //??
            questionArrayList = (ArrayList<Question>)jdbcTemplate.query(sql,new BeanPropertyRowMapper<Question>(Question.class));
//            questionArrayList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Question>(Question.class));
        }catch(DataAccessException e){

        }
        return questionArrayList;
    }
    /**
     *  根据标签查询题目
     */
    @Override
    public List<Question> queryQuestionType(String type){
        ArrayList<Question> questionArrayList = null ;
        String sql = "SELECT * FROM gp_question  WHERE 1=1 ";
        if(!type.equals("")){
            sql+=" AND question_type LIKE ?";
        }
        questionArrayList = (ArrayList<Question>)jdbcTemplate.query(sql,new BeanPropertyRowMapper<Question>(Question.class),"%"+type+"%");

        return questionArrayList;
    }

}
