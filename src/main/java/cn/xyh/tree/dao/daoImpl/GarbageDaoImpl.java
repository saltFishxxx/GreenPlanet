package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.GarbageDao;
import cn.xyh.tree.domain.Garbage;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GarbageDaoImpl implements GarbageDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<Garbage> selectAll() {
        String sql = "select * from gp_garbage";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Garbage>(Garbage.class));
    }

    @Override
    public int deleteGarbage(int gid) {
        return 0;
    }

    @Override
    public int updateGarbage(Garbage garbage) {
        return 0;
    }

    @Override
    public int addGarbage(Garbage garbage) {
        return 0;
    }
}
