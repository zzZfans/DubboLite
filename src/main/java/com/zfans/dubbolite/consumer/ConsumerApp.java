package com.zfans.dubbolite.consumer;

import com.zfans.dubbolite.entity.User;
import com.zfans.dubbolite.framework.ProxyFactory;
import com.zfans.dubbolite.provider.service.HelloService;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 17:54
 */
public class ConsumerApp {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory
                .getProxy(HelloService.class, "1");

        User user = new User("kaola", 6);

        System.out.println("发起 RPC 调用。");
        String result = helloService.sayHello(user);
        System.out.println("RPC 调用完毕。");

        System.out.println("结果：" + result);
    }
}
