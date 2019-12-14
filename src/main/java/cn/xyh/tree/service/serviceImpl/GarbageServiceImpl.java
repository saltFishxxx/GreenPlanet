package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.GarbageDao;
import cn.xyh.tree.dao.daoImpl.GarbageDaoImpl;
import cn.xyh.tree.domain.Garbage;
import cn.xyh.tree.service.GarbageService;
import cn.xyh.tree.util.toolImpl.DistanceUtil;

import java.util.List;

public class GarbageServiceImpl implements GarbageService {
    private GarbageDao garbageDao = new GarbageDaoImpl();

    @Override
    public List<Garbage> findAllGarbages() {
        return garbageDao.selectAll();
    }

    @Override
    public Garbage findNearGarbage(Garbage location) {
        List<Garbage> garbages = garbageDao.selectAll();
        int minNumber = 0;
        double min = DistanceUtil.calDistance(location, garbages.get(0));
        for (int i = 1 ; i < garbages.size() ; i++) {
            double temp = DistanceUtil.calDistance(garbages.get(i), location); //计算每个间的距离
            if ( temp < min) {
                min = temp;
                minNumber = i;
            }
        }
        return  garbages.get(minNumber);
    }
}
