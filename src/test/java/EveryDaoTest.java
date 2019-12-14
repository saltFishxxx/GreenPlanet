import cn.xyh.tree.dao.EverydayDao;
import cn.xyh.tree.dao.daoImpl.EveryDaoImpl;
import cn.xyh.tree.domain.EveryDay;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EveryDaoTest {
    private EverydayDao everydayDao = new EveryDaoImpl();

    @Test
    public void testFindEvery() {
        List<EveryDay> all = everydayDao.findAll();
        System.out.println(all);
    }

    @Test
    public void testRestart() {
        int i = everydayDao.restartAll();
        System.out.println(i);
    }

    @Test
    public void testUpdateStatus() {
//        int i = everydayDao.updateStatus(4);
//        System.out.println(i);
    }
}
