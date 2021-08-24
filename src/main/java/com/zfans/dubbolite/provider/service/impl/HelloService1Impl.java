package com.zfans.dubbolite.provider.service.impl;

import com.zfans.dubbolite.entity.User;
import com.zfans.dubbolite.provider.service.HelloService;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 17:57
 */
public class HelloService1Impl implements HelloService {

    public static final String VERSION = "1";

    @Override
    public String sayHello(String name) {
        return "Hello " + name + "! version: " + VERSION;
    }

    @Override
    public String sayHello(User user) {
        return "Hello " + user + "! version: " + VERSION;
    }
}
