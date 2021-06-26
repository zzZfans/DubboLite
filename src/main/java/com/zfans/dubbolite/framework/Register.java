package com.zfans.dubbolite.framework;

import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 15:53
 */
public interface Register {

    void regist(String interfaceName, URL url);

    List<URL> get(String interfaceName);

}
