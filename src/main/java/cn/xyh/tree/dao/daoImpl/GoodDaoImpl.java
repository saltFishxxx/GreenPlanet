package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.GoodDao;
import cn.xyh.tree.domain.Good;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GoodDaoImpl implements GoodDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public Good ifGood(Good good) {
        String sql = "select * from gp_good where news_id = ? and user_id = ? and good_type = ?";
        Good sqlGood = null;
        try {
            sqlGood = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Good>(Good.class),
                    good.getNews().getNewsId(), good.getUser().getUser_id(), good.getGoodType());
        }catch (Exception e) {

        }
        return sqlGood;
    }

    @Override
    public int goodCount(Good good) {
        String sql = "select count(*) from gp_good where good_type = ? and news_id = ?";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class, good.getGoodType(), good.getNews().getNewsId());
        }catch (Exception e) {

        }
        return count;
    }

    @Override
    public int addGood(Good good) {
        String sql = "insert into gp_good values (?,?,?,?)";
        return jdbcTemplate.update(sql, good.getNews().getNewsId(), good.getUser().getUser_id(),
                good.getGoodTime(), good.getGoodType());

    }

    @Override
    public List<News1> findGoodNews(int uid) {
        String sql = "select * from gp_news1 " +
                "where news_id in (select news_id from gp_good where user_id = ? and good_type = 2)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<News1>(News1.class), uid);
    }

    @Override
    public int deleteGood(Good good) {
        String sql = "delete from gp_good where news_id = ? " +
                "and user_id = ? and good_type = ?";
        return jdbcTemplate.update
                (sql, good.getNews().getNewsId(), good.getUser().getUser_id(), good.getGoodType());
    }
}
