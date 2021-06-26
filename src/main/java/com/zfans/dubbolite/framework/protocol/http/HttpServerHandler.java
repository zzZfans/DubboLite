package com.zfans.dubbolite.framework.protocol.http;

import com.zfans.dubbolite.framework.Invocation;
import com.zfans.dubbolite.provider.LocalRegister;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

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
            System.out.println("invocation: " + invocation);
            // 获取接口名
            String interfaceName = invocation.getInterfaceName();
            // 获取版本号
            String version = invocation.getVersion();
            // 获取实现类
            Class implClass = LocalRegister.get(interfaceName + version);
            // 通过 {方法名} 和 {参数类型列表} 唯一确定方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamType());
            // 反射执行方法
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
            System.out.println("result: " + result);
            // 将结果返回
            IOUtils.write(result, resp.getOutputStream(), Charset.defaultCharset());
        } catch (IOException |
                ClassNotFoundException |
                NoSuchMethodException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
