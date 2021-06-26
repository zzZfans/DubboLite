package com.zfans.dubbolite.consumer;

import com.zfans.dubbolite.framework.ProxyFactory;
import com.zfans.dubbolite.provider.service.HelloService;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 17:54
 */
public class ConsumerApp {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class, "1");

        String result = helloService.sayHello("Zfans");

        System.out.println(result);
    }
}
