package cn.xyh.tree.dao;

import cn.xyh.tree.domain.Service;

import java.util.List;

public interface ServiceDao {
    public List<Service> findAllServices(int uid, Integer status); //查询用户的所有服务
    public int addService(Service service); //添加服务
    public int updateServiceStatus(int status, int sid); //审核服务,1通过,2不通过
    public List<Service> findAll(); //查询所有人的服务
}
