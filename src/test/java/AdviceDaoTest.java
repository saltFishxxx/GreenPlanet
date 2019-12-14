import cn.xyh.tree.dao.AdviceDao;
import cn.xyh.tree.dao.daoImpl.AdviceDaoImpl;
import cn.xyh.tree.domain.Advice;
import cn.xyh.tree.domain.User;
import org.junit.jupiter.api.Test;

public class AdviceDaoTest {

    private AdviceDao adviceDao = new AdviceDaoImpl();

    @Test
    public void testAddAdivce() {
        Advice advice = new Advice();
        advice.setAdviceContent("fksjlfkjsdlfk");
        advice.setUser(new User(1));
        int i = adviceDao.addAdivce(advice);
        System.out.println(i);
    }
}
