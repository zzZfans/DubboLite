package com.zfans.dubbolite.register;

import com.zfans.dubbolite.framework.Register;
import com.zfans.dubbolite.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 1:39
 */
public class RemoteMapRegister implements Register {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();


    @Override
    public void regist(String interfaceName, URL url) {

        List<URL> list = REGISTER.get(interfaceName);

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();

        System.out.println("Registered through RemoteMapRegister.");
    }

    @Override
    public List<URL> get(String interfaceName) {

        REGISTER = getFile();

        List<URL> list = REGISTER.get(interfaceName);

        System.out.println("Get the urlList from RemoteMapRegister.");

        return list;
    }


    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/zfans/dubbolite/register/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/com/zfans/dubbolite/register/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Map<String, List<URL>> register = (Map<String, List<URL>>) objectInputStream.readObject();
            return register;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
