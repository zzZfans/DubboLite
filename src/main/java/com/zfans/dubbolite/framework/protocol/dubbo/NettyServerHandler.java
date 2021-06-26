package com.zfans.dubbolite.framework.protocol.dubbo;

import com.zfans.dubbolite.framework.Invocation;
import com.zfans.dubbolite.provider.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 11:12
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("In the NettyServerHandler:");

        // 调用对象
        Invocation invocation = (Invocation) msg;
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
        Object result = method.invoke(implClass.newInstance(), invocation.getParams());
        System.out.println("result: " + result);
        // 写回
        ctx.writeAndFlush(result);
    }
}
