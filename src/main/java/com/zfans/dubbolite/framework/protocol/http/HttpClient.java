package com.zfans.dubbolite.framework.protocol.http;

import com.zfans.dubbolite.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Author Zfans
 * @DateTime 2021/6/25 12:40
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {

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

            InputStream inputStream = httpURLConnection.getInputStream();

            String result = IOUtils.toString(inputStream, Charset.defaultCharset());

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
