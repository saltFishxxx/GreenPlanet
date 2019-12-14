package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.AdviceDao;
import cn.xyh.tree.domain.Advice;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdviceDaoImpl implements AdviceDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public int addAdivce(Advice advice) {
        String sql = "insert into gp_advice values (null, ?, ?)";
        return jdbcTemplate.update(sql,
                advice.getAdviceContent(), advice.getUser().getUser_id());
    }
}
