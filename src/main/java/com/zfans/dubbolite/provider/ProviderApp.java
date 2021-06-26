package com.zfans.dubbolite.provider;

import com.zfans.dubbolite.framework.*;
import com.zfans.dubbolite.provider.service.HelloService;
import com.zfans.dubbolite.provider.service.impl.HelloService1Impl;
import com.zfans.dubbolite.provider.service.impl.HelloService2Impl;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 17:54
 */
public class ProviderApp {
    public static void main(String[] args) {

        // 注册服务（接口名 + 版本号，实现多版本）
        // HelloService1: HelloService1Impl.class
        // HelloService2: HelloService2Impl.class

        // 本地注册
        LocalRegister.regist(HelloService.class.getName() + HelloService1Impl.VERSION,
                HelloService1Impl.class);
        LocalRegister.regist(HelloService.class.getName() + HelloService2Impl.VERSION,
                HelloService2Impl.class);

        // 注册中心注册
        URL url = new URL("localhost", 7869);
        Register register = RegisterFactory.getRegister();
        register.regist(HelloService.class.getName(), url);

        // 接收请求 netty http ...
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
