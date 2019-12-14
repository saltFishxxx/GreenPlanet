package cn.xyh.tree.domain;

public class Service {
    private Integer serviceId;
    private String serviceLocation;
    private String serviceDate;
    private String serviceTime;
    private User user;
    private Integer serviceStatus;
    private String strServiceStatus;

    public String getStrServiceStatus() {
        return strServiceStatus;
    }

    public void setStrServiceStatus(String strServiceStatus) {
        this.strServiceStatus = strServiceStatus;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceLocation='" + serviceLocation + '\'' +
                ", serviceDate='" + serviceDate + '\'' +
                ", serviceTime='" + serviceTime + '\'' +
                ", user=" + user +
                '}';
    }

    public Service(String serviceLocation, String serviceDate, String serviceTime, User user) {
        this.serviceLocation = serviceLocation;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.user = user;
    }

    public Service() {
    }
}
