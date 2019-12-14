package cn.xyh.tree.service.serviceImpl;

import cn.xyh.tree.dao.TreeDao;
import cn.xyh.tree.dao.daoImpl.TreeDaoImpl;
import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.service.TreeService;

public class TreeServiceImpl implements TreeService {
    private static final String FILE_URL = "http://106.13.113.4:8080/GreenFile";
    private static final String LEVEL_D="/testimg/tree1.png";
    private static final String LEVEL_C="/testimg/tree1.png";
    private static final String LEVEL_B="/testimg/tree1.png";
    private static final String LEVEL_A="/testimg/tree1.png";
    private TreeDao treeDao = new TreeDaoImpl();


    /**
     * 添加能量
     * @param user
     * @param energy
     * @return
     */
    @Override
    public boolean addEnergy(User user, int energy){
        return treeDao.addEnergy(user.getUser_id(),energy);
    }
//    boolean addTree(Tree tree, int userId)
    @Override
    public boolean addTree(Tree tree, User user){
        boolean flag = false;
        if (!treeDao.checkTree(user.getUser_id())){
//            tree.setTree_variety(FILE_URL+LEVEL_D);//树的样式默认值
            tree.setTree_video_url(FILE_URL+"/treeVoice/tree1.mp4");//url后面要修改
            treeDao.addTree(tree,user.getUser_id());
            flag = true ;
        }
        return flag;
    }

//    boolean delTreeById(int userId)

    /**
     * 查询树的信息
     * @param user
     * @return
     */
    @Override
    public Tree queryTreeById(User user){
        //是否存在树,存在返回值
        return treeDao.checkTree(user.getUser_id())
                ? treeDao.queryTreeById(user.getUser_id()) : null ;
    }

//    public boolean queryTree(User user){
//        return
//    }

    /**
     * 更改树的等级
     * @return
     */
//    public boolean updataTreeLevel(){
//
//    }


//    boolean checkTree(int userId)


}
