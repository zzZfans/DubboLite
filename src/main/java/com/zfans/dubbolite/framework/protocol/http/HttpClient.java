package com.zfans.dubbolite.framework.protocol.http;

import com.zfans.dubbolite.framework.Invocation;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author Zfans
 * @DateTime 2021/6/25 12:40
 */
public class HttpClient {

    public Object send(String hostname, Integer port, Invocation invocation) {

        try {

            URL url = new URL("http", hostname, port, "/");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            // 需要传输数据
            httpURLConnection.setDoOutput(true);
            // 获取输出流
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            objectOutputStream.close();

            // System.out.println("http 请求已发送至 " + hostname + ":" + port);

            InputStream inputStream = httpURLConnection.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Object result = objectInputStream.readObject();
            objectInputStream.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
