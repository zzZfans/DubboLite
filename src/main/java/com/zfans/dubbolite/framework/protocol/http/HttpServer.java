package com.zfans.dubbolite.framework.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 18:03
 */
public class HttpServer {
    public void start(String hostname, Integer port) {

        Tomcat tomcat = new Tomcat();

        // 日志级别设置为 WARN
        tomcat.setSilent(true);

        // 相当于配置 server.xml

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        Host host = new StandardHost();
        host.setName(hostname);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        // 所有请求都交给 dispatcher
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            // System.out.println("Tomcat 已启动对 " + hostname + ":" + port + " 的监听。");
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
