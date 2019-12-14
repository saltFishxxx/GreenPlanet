import cn.xyh.tree.dao.GarbageDao;
import cn.xyh.tree.dao.daoImpl.GarbageDaoImpl;
import cn.xyh.tree.domain.Garbage;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GarbageDaoTest {

    private GarbageDao garbageDao = new GarbageDaoImpl();

    @Test
    public void findAll() {
        List<Garbage> garbage = garbageDao.selectAll();
        System.out.println(garbage);
    }
}
