package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.EverydayDao;
import cn.xyh.tree.domain.EveryDay;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @author xyh
 */
public class EveryDaoImpl implements EverydayDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<EveryDay> findAll() {
        String sql = "select * from gp_everyday where everyday_status = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<EveryDay>(EveryDay.class));
    }

    @Override
    public int restartAll() {
        String sql = "update gp_everyday set everyday_status = 0";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int[] updateStatus(List<Object[]> eid) {
        String sql = "update gp_everyday set everyday_status = 1 where everyday_id = ?";
        return jdbcTemplate.batchUpdate(sql, eid);
    }

//    @Test
//    public void test() {
//        ArrayList<Object[]> objects = new ArrayList<>();
//        for(int i = 0; i < 4; i++){
//            Object[] ints = new Object[1];
//            ints[0] = i + 1;
//            objects.add(ints);
//        }
//        int[] ints = updateStatus(objects);
//        System.out.println(Arrays.toString(ints));
//    }
}
