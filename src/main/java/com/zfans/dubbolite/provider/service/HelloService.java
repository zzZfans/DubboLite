package com.zfans.dubbolite.provider.service;

import com.zfans.dubbolite.entity.User;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 17:56
 */
public interface HelloService {

    String sayHello(String name);

    String sayHello(User user);
}
