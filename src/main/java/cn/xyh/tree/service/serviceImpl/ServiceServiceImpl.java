package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.ServiceDao;
import cn.xyh.tree.dao.daoImpl.ServiceDaoImpl;
import cn.xyh.tree.domain.Service;
import cn.xyh.tree.service.ServiceService;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {
    private ServiceDao serviceDao = new ServiceDaoImpl();

    @Override
    public List<Service> findByUid(int uid, Integer stauts) {
        List<Service> allServices = serviceDao.findAllServices(uid, stauts);
        for (Service service : allServices) {
            if (service.getServiceStatus() == 0){
                service.setStrServiceStatus("正在审核");
            }else if (service.getServiceStatus() == 1) {
                service.setStrServiceStatus("审核通过");
            }else if (service.getServiceStatus() == 2) {
                service.setStrServiceStatus("审核失败");
            }
        }
        return allServices;
    }

    @Override
    public boolean inserService(Service service) {
        boolean flag = false;
        if (serviceDao.addService(service) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateService(String sid, int status) {
        if (sid == null || "".equals(sid)) {
            return false;
        }
        boolean flag = false;
        if (serviceDao.updateServiceStatus(status, Integer.parseInt(sid)) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Service> findAll() {
        return serviceDao.findAll();
    }
}