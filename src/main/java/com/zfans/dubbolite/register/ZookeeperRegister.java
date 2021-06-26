package com.zfans.dubbolite.register;

import com.alibaba.fastjson.JSONObject;
import com.zfans.dubbolite.framework.Register;
import com.zfans.dubbolite.framework.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 14:13
 */
public class ZookeeperRegister implements Register {

    static CuratorFramework client;

    static {
        client = CuratorFrameworkFactory
                .newClient("localhost:2181", new RetryNTimes(3, 1000));
        client.start();
    }

    private static final Map<String, List<URL>> REGISTER = new HashMap<>();

    @Override
    public void regist(String interfaceName, URL url) {
        try {
            String result = client
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(String.format("/dubbo/service/%s/%s", interfaceName, JSONObject.toJSONString(url)), null);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Registered through ZookeeperRegister.");
    }

    @Override
    public List<URL> get(String interfaceName) {

        List<URL> urlList = new ArrayList<>();

        try {
            List<String> result = client
                    .getChildren()
                    .forPath(String.format("/dubbo/service/%s", interfaceName));

            for (String urlstr : result) {
                urlList.add(JSONObject.parseObject(urlstr, URL.class));
            }

            REGISTER.put(interfaceName, urlList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Get the urlList from ZookeeperRegister.");
        return urlList;
    }
}