import cn.xyh.tree.dao.ServiceDao;
import cn.xyh.tree.dao.daoImpl.ServiceDaoImpl;
import cn.xyh.tree.domain.Service;
import cn.xyh.tree.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServiceDaoTest {
    private ServiceDao serviceDao = new ServiceDaoImpl();

    @Test
    public void testFindAll() {
        List<Service> allServices = serviceDao.findAllServices(28, null);
        System.out.println(allServices);
    }

    @Test
    public void testInsertService() {
        User user = new User();
        user.setUser_id(28);
        Service service = new Service("大学路", "1998-03-01", "19:22:31", user);
        System.out.println(serviceDao.addService(service));
    }

    @Test
    public void testUpdateService() {
        System.out.println(serviceDao.updateServiceStatus(2, 3));
    }

    @Test
    public void testFindAll1() {
        List<Service> all = serviceDao.findAll();
        System.out.println(all);
    }
}
