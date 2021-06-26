package com.zfans.dubbolite.framework.protocol.http;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author Zfans
 * @DateTime 2021/6/24 19:52
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // 处理请求的逻辑
        new HttpServerHandler().handler(req, resp);
    }
}
