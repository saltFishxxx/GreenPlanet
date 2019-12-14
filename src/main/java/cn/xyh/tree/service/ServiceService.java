package cn.xyh.tree.service;

import cn.xyh.tree.domain.Service;

import java.util.List;

public interface ServiceService {
    public List<Service> findByUid(int uid, Integer status); //查询用户的服务
    public boolean inserService(Service service); //提交服务
    public boolean updateService(String sid, int status); //审核通过与否
    public List<Service> findAll();
}
