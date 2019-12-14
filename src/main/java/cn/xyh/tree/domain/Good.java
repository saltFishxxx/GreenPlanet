package cn.xyh.tree.domain;

import java.util.Date;

public class Good {
    private User user;
    private News1 news;
    private Date goodTime;
    private int goodType; //1为点赞，2为收藏

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News1 getNews() {
        return news;
    }

    public void setNews(News1 news) {
        this.news = news;
    }

    public Date getGoodTime() {
        return goodTime;
    }

    public void setGoodTime(Date goodTime) {
        this.goodTime = goodTime;
    }

    public int getGoodType() {
        return goodType;
    }

    public void setGoodType(int goodType) {
        this.goodType = goodType;
    }

    @Override
    public String toString() {
        return "Good{" +
                "user=" + user +
                ", news=" + news +
                ", goodTime=" + goodTime +
                ", goodType=" + goodType +
                '}';
    }
}
