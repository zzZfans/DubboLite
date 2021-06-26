package com.zfans.dubbolite.framework.protocol.http;

import com.zfans.dubbolite.framework.Invocation;
import com.zfans.dubbolite.framework.Protocol;
import com.zfans.dubbolite.framework.URL;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 12:26
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
