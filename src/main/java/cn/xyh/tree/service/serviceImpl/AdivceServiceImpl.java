package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.AdviceDao;
import cn.xyh.tree.dao.daoImpl.AdviceDaoImpl;
import cn.xyh.tree.domain.Advice;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.AdviceService;

public class AdivceServiceImpl implements AdviceService {
    private AdviceDao adviceDao = new AdviceDaoImpl();

    @Override
    public boolean addAdvice(String content, int uid) {
        boolean flag = false;
        Advice advice = new Advice();
        advice.setAdviceContent(content);
        advice.setUser(new User(uid));
        if (adviceDao.addAdivce(advice) > 0) {
            flag = true;
        }
        return flag;
    }
}
