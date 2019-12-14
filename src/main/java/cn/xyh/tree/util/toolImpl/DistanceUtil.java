package cn.xyh.tree.util.toolImpl;

import cn.xyh.tree.domain.Garbage;
import org.junit.jupiter.api.Test;

public class DistanceUtil {
    private static int EARTH_RADIUS = 6471 * 1000; //地球的半径

    private DistanceUtil() {}

    /**
     * 通过垃圾桶的位置得到距离
     * 距离 = haversin(纬度差值)+cos(纬度1)cos(纬度2)haversin(经度差值)
     * @param garbage1
     * @param garbage2
     * @return
     */
    public static int calDistance(Garbage garbage1, Garbage garbage2) {
        //将纬度的度数转换为弧度
        double latitude1 = convertDegreeToRadians(garbage1.getGarbageLatitude());
        double latitude2 = convertDegreeToRadians(garbage2.getGarbageLatitude());
        //将经度的度数转换为弧度
        double longitude1 = convertDegreeToRadians(garbage1.getGarbageLongitude());
        double longitude2 = convertDegreeToRadians(garbage2.getGarbageLongitude());

        //计算纬度的差值
        double latitudeDifference = Math.abs(latitude1 - latitude2);
        //计算经度的差值
        double longitudeDifference = Math.abs(longitude1 - latitude2);

        double h = getHaversin(latitudeDifference)+Math.cos(latitude1)
                *Math.cos(latitude2)*getHaversin(longitudeDifference);

        //返回两点间距离
        int distance =  (int)(2 * EARTH_RADIUS * Math.asin(Math.sqrt(h)));
        return  distance;
    }

    /**
     * 获取haversin的值
     * @param value
     * @return
     */
    private static double getHaversin(double value) {
        return (1-Math.cos(value))/2;
    }

    /**
     * 将角度转为弧度
     * @param degree
     * @return
     */
    private static double convertDegreeToRadians(double degree) {
        return degree/180 * Math.PI;
    }

    /**
     * 将弧度转换为角度
     * @param radians
     * @return
     */
    private static double convertRadiansToDegree(double radians) {
        return 180/Math.PI * radians;
    }


    @Test
    public void test() {
        Garbage garbage = new Garbage(22.333, 22.444);
        Garbage garbage1 = new Garbage(24.444, 25.111);
        double v = calDistance(garbage1, garbage);
        System.out.println(v);
    }

}
