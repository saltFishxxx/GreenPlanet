package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.NewsDao;
import cn.xyh.tree.domain.News;
import cn.xyh.tree.domain.News1;
import cn.xyh.tree.util.toolImpl.DateUtil;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsDaoImpl implements NewsDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<News> findAll() {
        String sql = "select * from gp_news";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<News>(News.class));
    }

    @Override
    public List<News1> findAll1(String category) {
        String sql = "select * from gp_news1 where 1 = 1";
        if (category != null && !"".equals(category)) {
            sql += " and news_type = ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<News1>(News1.class), category);
        }
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<News1>(News1.class));
    }


    @Override
    public int addNews(News news) {
        String sql = "insert into gp_news values (null, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, news.getNewsName(), news.getNewsType(), news.getNewsUrl(), news.getNewsDatetime());
    }

    //添加新闻的同时并且返回自增id
    @Override
    public int addNews1(final News1 news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into gp_news1 values (null, ?, ?, ?, ?,?)";
//        return jdbcTemplate.update(sql, news.getNewsName(), news.getNewsType(), news.getNewsContent(), news.getNewsDatetime(), news.getNewsLittletype());
        //返回自增长id
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, news.getNewsName());
                ps.setString(2, news.getNewsType());
                ps.setString(3, news.getNewsContent());
                ps.setDate(4, DateUtil.transform(news.getNewsDatetime()));
                ps.setString(5, news.getNewsLittletype());
                return ps;
            }
        }, keyHolder);
        System.out.println(keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public News1 findNews1ById(int id) {
        String sql = "select * from gp_news1 where news_id = ?";
        News1 news1 = null;
        try {
            news1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<News1>(News1.class), id);
        }catch (Exception e) {

        }
        return news1;
    }

    @Override
    public int insertImgIds(int[] imgIds,int newsId) {
        String sql = "update gp_news_img set news_id = ? where news_img_id in ( ?";
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(newsId);
        integers.add(imgIds[0]);
        for(int i = 1; i < imgIds.length; i++){
            sql +=  ",?";
            integers.add(imgIds[i]);
        }
        sql += ")";
        System.out.println(sql);
        return jdbcTemplate.update(sql, integers.toArray());
    }

    @Override
    public int deleteNews(int nid) {
        String sql = "delete from gp_news1 where news_id = ?";
        return jdbcTemplate.update(sql, nid);
    }

    /**
     * 没有最新修改时间, 通过判断条件的多少来更新
     * @param news1
     * @return
     */
    @Override
    public int updateNews(News1 news1) {
        String sql = "update gp_news1 set news_name = ?, " +
                "news_type = ?, news_content = ?, news_littletype = ? where news_id = ?";
        return jdbcTemplate.update(sql, news1.getNewsName(), news1.getNewsType(),
                news1.getNewsType(), news1.getNewsLittletype(), news1.getNewsId());
    }

    @Override
    public List<News1> findQuikNews() {
        String sql = "select * from gp_news1 order by news_datetime desc limit 0,4;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<News1>(News1.class));
    }

    public int findNewsCount() {
        String sql = "select count(*) from gp_news1";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch (Exception e) {

        }
        return count;
    }
}
