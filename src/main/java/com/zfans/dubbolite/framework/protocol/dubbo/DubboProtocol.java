package com.zfans.dubbolite.framework.protocol.dubbo;

import com.zfans.dubbolite.framework.Invocation;
import com.zfans.dubbolite.framework.Protocol;
import com.zfans.dubbolite.framework.URL;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 12:25
 */
public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
