package cn.xyh.tree.service;

import cn.xyh.tree.dao.GarbageDao;
import cn.xyh.tree.dao.daoImpl.GarbageDaoImpl;
import cn.xyh.tree.domain.Garbage;

import java.util.List;

public interface GarbageService {
    public List<Garbage> findAllGarbages();
    public Garbage findNearGarbage(Garbage location);
}
