package com.zfans.dubbolite.framework;

import java.util.List;
import java.util.Random;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 1:47
 */
public class LoadBalance {
    public static URL random(List<URL> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }
}
