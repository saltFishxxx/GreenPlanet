import cn.xyh.tree.util.toolImpl.SerializableUtil;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        int[] nums = {-1};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int max = 0;
        for(int i = 0;i<nums.length;i++){
            int sum = 0;
            for(int j=i;j<nums.length;j++){
                sum += nums[j];
                if(sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
