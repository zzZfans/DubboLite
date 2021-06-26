package com.zfans.dubbolite.framework;

import com.zfans.dubbolite.framework.protocol.dubbo.DubboProtocol;
import com.zfans.dubbolite.framework.protocol.http.HttpProtocol;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 12:28
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        // 工厂模式
        String name = System.getProperty("protocolName");
        if (name == null || "".equals(name)) {
            name = "dubbo";
        }
        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }
        return new DubboProtocol();
    }
}
