package cn.xyh.tree.domain;

public class Tree {
    private int tree_id ;
    private int tree_power;
    private int tree_lv;
    private int user_id;
    private String tree_video_url;  //树
    private String tree_variety;    // 树的种类

    public int getTree_id() {
        return tree_id;
    }

    public void setTree_id(int tree_id) {
        this.tree_id = tree_id;
    }

    public int getTree_power() {
        return tree_power;
    }

    public void setTree_power(int tree_power) {
        this.tree_power = tree_power;
    }

    public int getTree_lv() {
        return tree_lv;
    }

    public void setTree_lv(int tree_lv) {
        this.tree_lv = tree_lv;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTree_video_url() {
        return tree_video_url;
    }

    public void setTree_video_url(String tree_video_url) {
        this.tree_video_url = tree_video_url;
    }

    public String getTree_variety() {
        return tree_variety;
    }

    public void setTree_variety(String tree_variety) {
        this.tree_variety = tree_variety;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "tree_id=" + tree_id +
                ", tree_power=" + tree_power +
                ", tree_lv=" + tree_lv +
                ", user_id=" + user_id +
                ", tree_video_url='" + tree_video_url + '\'' +
                ", tree_variety='" + tree_variety + '\'' +
                '}';
    }
}
