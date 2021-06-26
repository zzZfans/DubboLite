package com.zfans.dubbolite.framework;

import com.zfans.dubbolite.register.RemoteMapRegister;
import com.zfans.dubbolite.register.ZookeeperRegister;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 15:56
 */
public class RegisterFactory {
    public static Register getRegister() {
        // 工厂模式
        String name = System.getProperty("registerName");
        if (name == null || "".equals(name)) {
            name = "local";
        }
        switch (name) {
            case "local":
                return new RemoteMapRegister();
            case "zookeeper":
                return new ZookeeperRegister();
            default:
                break;
        }
        return new RemoteMapRegister();
    }
}
