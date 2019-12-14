package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.NewsImgDao;
import cn.xyh.tree.domain.NewsImg;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class NewsImgDaoImpl implements NewsImgDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public int upload(final NewsImg newsImg) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into gp_news_img(news_img_id, news_img_url) values (null,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, newsImg.getNewsImgUrl());
                return statement;
            }
        }, keyHolder);
//        return jdbcTemplate.update(sql, newsImg.getNewsImgUrl(), newsImg.getNews().getNewsId());
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<NewsImg> downLoad(int nid) {
        String sql = "select * from gp_news_img where news_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NewsImg>(NewsImg.class), nid);
    }
}
