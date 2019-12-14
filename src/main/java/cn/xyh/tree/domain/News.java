package cn.xyh.tree.domain;

import java.util.Date;
import java.util.List;

public class News {
    private Integer newsId;
    private String newsName;
    private String newsType;
    private String newsUrl;
    private Date newsDatetime;
    private List<NewsImg> newsImgs;

    public List<NewsImg> getNewsImgs() {
        return newsImgs;
    }

    public void setNewsImgs(List<NewsImg> newsImgs) {
        this.newsImgs = newsImgs;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public Date getNewsDatetime() {
        return newsDatetime;
    }

    public void setNewsDatetime(Date newsDatetime) {
        this.newsDatetime = newsDatetime;
    }
}
