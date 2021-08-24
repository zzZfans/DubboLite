package com.zfans.dubbolite.framework.protocol.http;

import com.zfans.dubbolite.framework.Invocation;
import com.zfans.dubbolite.provider.LocalRegister;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 19:55
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("In the HttpServerHandler:");

        try {
            // 从输入流中获取调用对象
            ServletInputStream reqInputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(reqInputStream);
            Invocation invocation = (Invocation) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("调用对象：" + invocation);
            // 获取接口名
            String interfaceName = invocation.getInterfaceName();
            // 获取版本号
            String version = invocation.getVersion();
            // 获取实现类
            Class implClass = LocalRegister.get(interfaceName + version);
            // 通过 {方法名} 和 {参数类型列表} 唯一确定方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamType());
            // 反射执行方法
            Object result = method.invoke(implClass.newInstance(), invocation.getParams());
            System.out.println("反射执行结果：" + result);
            // 将结果返回
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(resp.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
