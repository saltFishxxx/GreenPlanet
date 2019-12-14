package cn.xyh.tree.domain;

public class EveryDay {
    private Integer everydayId;
    private String everydayContent;
    private Integer getStatus;

    public Integer getEverydayId() {
        return everydayId;
    }

    public void setEverydayId(Integer everydayId) {
        this.everydayId = everydayId;
    }

    public String getEverydayContent() {
        return everydayContent;
    }

    public void setEverydayContent(String everydayContent) {
        this.everydayContent = everydayContent;
    }

    public Integer getGetStatus() {
        return getStatus;
    }

    public void setGetStatus(Integer getStatus) {
        this.getStatus = getStatus;
    }

    @Override
    public String toString() {
        return "EveryDay{" +
                "everydayId=" + everydayId +
                ", everydayContent='" + everydayContent + '\'' +
                ", getStatus=" + getStatus +
                '}';
    }
}
