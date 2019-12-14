package cn.xyh.tree.util.toolImpl;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

/**
 *
 */
public class RandomUtil {

    /**
     * 返回7位随机数字,找回密码验证
     */
    public int randomMessge(){
        return new Random().nextInt(5000000)+new Random().nextInt(4900000);
    }

}
