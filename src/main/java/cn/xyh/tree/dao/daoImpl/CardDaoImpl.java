package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.CardDao;
import cn.xyh.tree.dao.QuestionDao;
import cn.xyh.tree.domain.Card;
import cn.xyh.tree.domain.Question;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    //日志
    private static final Logger  log   = Logger.getLogger(UserDaoImpl.class);
    //使用了jdbcTemplate
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    // 修改card未完成
    @Override
    public boolean updateCardById(Card card){
        Boolean flag = false ;
        String sql = "";
        int update = jdbcTemplate.update(sql);
        if(update>0){
            flag = true;
        }
        return flag ;
    }
    // 删除card未完成
    @Override
    public boolean delCardById(Card card){
        Boolean flag = false ;
        String sql = " ";
        int update = jdbcTemplate.update(sql);
        if(update>0){
            flag = true;
        }
        return flag ;
    }
    // 增加card
    @Override
    public boolean addCardById(Card card){
        Boolean flag = false ;
        String sql = "insert into gp_card(card_type,card_content,card_img) values(?,?,?,?)  ";
        int update = jdbcTemplate.update(sql, card.getCardType(), card.getCardContent(), card.getCardName());
        if(update>0){
            flag = true;
        }
        return flag ;
    }
    //查询
    @Override
    public List<Card> queyCardById(String UserId){
        ArrayList<Card> cardList = null;
        String sql = "SELECT * FROM gp_card WHERE user_id = ?";
        try {
            cardList = (ArrayList<Card>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Card>(Card.class));
        }catch (DataAccessException e){}
        return cardList;
    }
    public boolean isEmpty(String UserId){
        return queyCardById(UserId)!=null ? true : false;
    }

}
