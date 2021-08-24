package com.zfans.dubbolite.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 20:42
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceNameAndVersion, Class implClass) {
        // System.out.println(interfaceNameAndVersion + " 已注册到本地。");
        map.put(interfaceNameAndVersion, implClass);
    }

    public static Class get(String interfaceNameAndVersion) {
        return map.get(interfaceNameAndVersion);
    }
}
