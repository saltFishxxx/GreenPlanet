package cn.xyh.tree.domain;

public class Garbage {
    private Integer garbageId;
    private Double garbageLongitude;
    private Double garbageLatitude;

    public Integer getGarbageId() {
        return garbageId;
    }

    public void setGarbageId(Integer garbageId) {
        this.garbageId = garbageId;
    }

    public Double getGarbageLatitude() {
        return garbageLatitude;
    }

    public void setGarbageLatitude(Double garbageLatitude) {
        this.garbageLatitude = garbageLatitude;
    }

    public Double getGarbageLongitude() {
        return garbageLongitude;
    }

    public void setGarbageLongitude(Double garbageLongitude) {
        this.garbageLongitude = garbageLongitude;
    }

    @Override
    public String toString() {
        return "Garbage{" +
                "garbageId=" + garbageId +
                ", garbageLongitude=" + garbageLongitude +
                ", garbageLatitude=" + garbageLatitude +
                '}';
    }

    public Garbage(Double garbageLongitude, Double garbageLatitude) {
        this.garbageLongitude = garbageLongitude;
        this.garbageLatitude = garbageLatitude;
    }

    public Garbage() {}
}
