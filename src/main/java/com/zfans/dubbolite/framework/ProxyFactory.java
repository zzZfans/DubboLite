package com.zfans.dubbolite.framework;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 1:01
 */
public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass, String version) {
        Object proxyInstance = Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                // InvocationHandler::invoke
                (proxy, method, args) -> {

                    Invocation invocation = new Invocation(
                            // 接口名
                            interfaceClass.getName(),
                            // 方法名
                            method.getName(),
                            // 版本
                            version,
                            // 参数列表
                            args,
                            // 参数类型列表
                            method.getParameterTypes()
                    );
                    System.out.println("调用对象：" + invocation);

                    // 在注册中心找到对应服务的 url
                    Register register = RegisterFactory.getRegister();
                    List<URL> urlList = register.get(interfaceClass.getName());
                    System.out.println("地址端口列表：" + urlList);

                    // 负载均衡
                    URL url = LoadBalance.random(urlList);

                    // 通信协议 netty http ...
                    Protocol protocol = ProtocolFactory.getProtocol();

                    // 发送请求 接收结果
                    Object result = protocol.send(url, invocation);

                    return result;
                });
        return (T) proxyInstance;
    }
}
