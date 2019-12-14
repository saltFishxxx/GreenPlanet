package cn.xyh.tree.domain;

import java.util.List;

public class NewsImg {
    private String newsImgUrl;
    private Integer newsImgId;
    private News1 news;

    public News1 getNews() {
        return news;
    }

    public void setNews(News1 news) {
        this.news = news;
    }

    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }

    public Integer getNewsImgId() {
        return newsImgId;
    }

    public void setNewsImgId(Integer newsImgId) {
        this.newsImgId = newsImgId;
    }

    @Override
    public String toString() {
        return "NewsImg{" +
                "newsImgUrl='" + newsImgUrl + '\'' +
                ", newsImgId=" + newsImgId +
                ", news=" + news +
                '}';
    }
}
