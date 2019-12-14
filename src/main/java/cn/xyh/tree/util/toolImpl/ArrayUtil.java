package cn.xyh.tree.util.toolImpl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    private ArrayUtil(){}

    public static int[] toInts(String[] strings) {
        int[] ints = new int[strings.length];
        for(int i = 0; i < strings.length; i++){
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    public static int[] toArray(List list, int index, int length) {
        int[] ints = new int[length];
        for(int i = index; i < list.size(); i++){
            if (length == 0) {
                ints[i] = (int) list.get(i);
            }
            length --;
        }
        return ints;
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
    }
}
