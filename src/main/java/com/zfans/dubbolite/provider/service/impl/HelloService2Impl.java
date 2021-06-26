package com.zfans.dubbolite.provider.service.impl;

import com.zfans.dubbolite.provider.service.HelloService;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 21:12
 */
public class HelloService2Impl implements HelloService {

    public static final String VERSION = "2";

    @Override
    public String sayHello(String name) {
        return "Hello " + name + "! version: " + VERSION;
    }
}
