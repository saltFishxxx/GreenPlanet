package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.ServiceDao;
import cn.xyh.tree.domain.Service;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ServiceDaoImpl implements ServiceDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<Service> findAllServices(int uid, Integer status) {
        String sql = "select * from gp_service where user_id = ? order by service_date desc,service_time desc;";
        if (status == null ) {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Service>(Service.class), uid);
        }else {
            sql += " and service_status = ? order by service_date desc,service_time desc;";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Service>(Service.class), uid, status);
        }
    }

    @Override
    public int addService(Service service) {
        String sql = "insert into gp_service(service_location, service_date," +
                "service_time, user_id) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, service.getServiceLocation(), service.getServiceDate(),
                service.getServiceTime(), service.getUser().getUser_id());
    }

    @Override
    public int updateServiceStatus(int status, int sid) {
        String sql = "update gp_service set service_status = ? where service_id = ?";
        return jdbcTemplate.update(sql, status, sid);
    }

    @Override
    public List<Service> findAll() {
        String sql = "select * from gp_service where service_status = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Service>(Service.class));
    }
}
