package cn.xyh.tree.util.toolImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private DateUtil() {};
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 将javadate转换为数据库date
     * @param date
     * @return
     */
    public static java.sql.Date transform(
    Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 字符串转date
     * @param date
     * @return
     */
    public static Date StringToDate(String date) {
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * date转字符串
     * @param date
     * @return
     */
    public static String DateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    /**
     * 计算是否在范围内
     * @return
     */
    public static int CalStatus(Date start, Date end, Date target) {
        long startMill = start.getTime();
        long endMill = end.getTime();
        long targetMill = target.getTime();

        int status = -1;
        if (targetMill < startMill) {
            status = 0;
        }else if (targetMill > startMill && targetMill < endMill) {
            status = 1;
        }else if (targetMill > endMill) {
            status = 2;
        }
        return status;
    }
}
