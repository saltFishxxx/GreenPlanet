import cn.xyh.tree.dao.TreeDao;
import cn.xyh.tree.dao.daoImpl.TreeDaoImpl;
import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

public class TreeDaoImplTest {
    private static final String URL = "http://106.13.113.4:8080/GreenFile";
    private static final String LEVEL_D="/testimg/tree1.png";
    TreeDao treeDao = new TreeDaoImpl() ;
    @Test
    public void addEnergy( ){
        int energy = 0;
        User user = new User();
        user.setUser_id(1);
//        TreeDao dao = new TreeDaoImpl();
        if(treeDao.addEnergy(user.getUser_id(),energy)){
            System.out.println("添加成功");
        }
    }
    @Test
    public void addTree(){
        Tree tree = new Tree();
        int userId = 1;
        if (!treeDao.checkTree(userId)){
            treeDao.addTree(tree,userId);
        }else{
            System.out.println(" 已经有树了");
        }
        System.out.println(treeDao.checkTree(2));

    }
    @Test
    public void  delTreeById(){
        int userId = 1;
    }
    @Test
    public void  queryTreeById(){
        int userId = 1;
        Tree tree = treeDao.queryTreeById(userId);
        if(tree.getTree_variety().equals("D")){
            tree.setTree_variety(URL+LEVEL_D);
        }
        tree.setTree_video_url(URL+tree.getTree_video_url());

        JSONObject jsonTree =  JSONObject.fromObject(tree);
        System.out.println(jsonTree);

//        URL+"\testimg\tree1.png"
    }
    @Test
    public void  checkTree(){
        int userId = 1;
        System.out.println(treeDao.checkTree(userId));
    }
}
