package cn.xyh.tree.domain;

public class ActivityUser {
    private User user;
    private Activity activity;
    private Integer pcount;

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ActivityUser{" +
                "user=" + user +
                ", activity=" + activity +
                ", pcount=" + pcount +
                '}';
    }
}
