package cn.xyh.tree.domain;

import cn.xyh.tree.util.toolImpl.DateUtil;

import java.util.Date;

public class Activity {
    private Integer activityId;
    private String activityName;
    private Integer activityPnumber;
    private String activityImg;
    private String activityLocation;
    private String activityContent;
    private Date activityStart;
    private Date activityEnd;
    private String stringStart;
    private String stringEnd;
    private int ActivityStatus; //活动状态,0未开始,1进行中,2已结束

    public String getStringStart() {
        return stringStart;
    }

    public void setStringStart(String stringStart) {
        this.stringStart = stringStart;
    }

    public String getStringEnd() {
        return stringEnd;
    }

    public void setStringEnd(String stringEnd) {
        this.stringEnd = stringEnd;
    }

    public int getActivityStatus() {
        return ActivityStatus;
    }

    public void setActivityStatus(int activityStatus) {
        ActivityStatus = activityStatus;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer acitivityId) {
        this.activityId = acitivityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityPnumber() {
        return activityPnumber;
    }

    public void setActivityPnumber(Integer activityPnumber) {
        this.activityPnumber = activityPnumber;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public Date getActivityStart1() {
        return activityStart;
    }

    public void setActivityStart1(Date activityStart) {
        this.activityStart = activityStart;
        this.stringStart = DateUtil.DateToString(activityStart);
    }

    public Date getActivityEnd1() {
        return activityEnd;
    }

    public void setActivityEnd1(Date activityEnd) {
        this.activityEnd = activityEnd;
        this.stringEnd = DateUtil.DateToString(activityEnd);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "acitivityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityPnumber='" + activityPnumber + '\'' +
                ", activityImg='" + activityImg + '\'' +
                ", activityLocation='" + activityLocation + '\'' +
                ", activityContent='" + activityContent + '\'' +
                ", activityStart=" + activityStart +
                ", activityEnd=" + activityEnd +
                '}';
    }
}
