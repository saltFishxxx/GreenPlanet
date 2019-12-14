package cn.xyh.tree.domain;

import java.util.Date;
import java.util.List;

public class News1 {
    private Integer newsId;
    private String newsName;
    private String newsType;
    private String newsContent;
    private Date newsDatetime;
    private String newsLittletype;
    private Integer newsGoodnumber;
    private List<NewsImg> newsImgs;
    private int goodCount;
    private String StringDateTime;

    public String getStringDateTime() {
        return StringDateTime;
    }

    public void setStringDateTime(String stringDateTime) {
        StringDateTime = stringDateTime;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public List<NewsImg> getNewsImgs() {
        return newsImgs;
    }

    public void setNewsImgs(List<NewsImg> newsImgs) {
        this.newsImgs = newsImgs;
    }

    public Integer getNewsGoodnumber() {
        return newsGoodnumber;
    }

    public void setNewsGoodnumber(Integer newsGoodnumber) {
        this.newsGoodnumber = newsGoodnumber;
    }

    public String getNewsLittletype() {
        return newsLittletype;
    }

    public void setNewsLittletype(String newsLittletype) {
        this.newsLittletype = newsLittletype;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
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

    public Date getNewsDatetime() {
        return newsDatetime;
    }

    public void setNewsDatetime(Date newsDatetime) {
        this.newsDatetime = newsDatetime;
    }


    @Override
    public String toString() {
        return "News1{" +
                "newsId=" + newsId +
                ", newsName='" + newsName + '\'' +
                ", newsType='" + newsType + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsDatetime=" + newsDatetime +
                ", newsLittletype='" + newsLittletype + '\'' +
                ", newsGoodnumber=" + newsGoodnumber +
                '}';
    }
}
