package cn.xyh.tree.util.toolImpl;

public class StrUtil {
    private StrUtil() {}

    public static boolean ifNull(String... strings) {
        boolean flag = false;
        for(int i = 0; i < strings.length; i++){
            if (strings[i] == null || "".equals(strings[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
