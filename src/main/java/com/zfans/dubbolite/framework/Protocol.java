package com.zfans.dubbolite.framework;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 12:23
 */
public interface Protocol {

    void start(URL url);

    Object send(URL url, Invocation invocation);
}
