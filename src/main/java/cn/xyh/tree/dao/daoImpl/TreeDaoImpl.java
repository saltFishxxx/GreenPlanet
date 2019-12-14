package cn.xyh.tree.dao.daoImpl;

import cn.xyh.tree.dao.TreeDao;
import cn.xyh.tree.domain.Tree;
import cn.xyh.tree.domain.User;
import cn.xyh.tree.util.toolImpl.JDBCUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TreeDaoImpl implements TreeDao {
        private static final Logger log = Logger.getLogger(TreeDaoImpl.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

        /**
         * 根据用户ID来进行和获得能量增加能量
         * @param userId
         * @param energy
         *      -获得能量
         * @return
         */
        @Override
        public boolean addEnergy(int userId, int energy){
                String sql = "UPDATE gp_tree set tree_power = tree_power + ? WHERE user_id = ? ";
                boolean flag = false ;
                int addDate = jdbcTemplate.update(sql,energy,userId);
                if(addDate >0){
                        flag = true ;
                }
                return flag ;
        }

        /**
         * 用户点击领养树的时候，创建树的时候，每个用户唯一拥有一棵树。
         * @param tree
         * @param userId
         * @return
         */
        @Override
        public boolean addTree(Tree tree , int userId){
                String sql = "INSERT INTO gp_tree(user_id,tree_video_url) VALUES (?,?)  ";
                boolean flag = false ;
                int addDate = jdbcTemplate.update(sql,userId,tree.getTree_video_url());
                if(addDate>0){
                        flag =true ;
                }
                return flag ;
        }

        @Override
        public boolean delTreeById(int userId){
                String sql = "DELETE  FROM gp_tree WHERE user_id = ?";
                boolean flag = false ;
                int addDate = jdbcTemplate.update(sql,userId);
                if(addDate >0){
                        flag = true ;
                }
                return flag ;
        }

        /**
         * 查询用户的树的基本信息
         * @param userId
         * @return
         */
        @Override
        public Tree queryTreeById(int userId){
                String sql = "SELECT * FROM gp_tree WHERE user_id = ? ";
                Tree tree = null;
                try {
                        tree = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Tree.class), userId);
                }catch(DataAccessException e){
                        log.info(e);
                }
                return tree ;
        }

        /**
         * 校验用户是否拥有树木
         * @param userId
         * @return
         */
        @Override
        public boolean checkTree(int userId){
                return queryTreeById(userId) != null ? true : false;
        }
}
