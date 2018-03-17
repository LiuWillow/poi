package com.lwl.bishe.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * date  2018/3/17
 * author liuwillow
 **/
public class HttpUtil {
    private HttpUtil(){}

    public static <T> String sendGet(String baseUrl, T t){
        String finalUrl = spliceUrlAndParams(baseUrl, t);
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(finalUrl);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder builder = new StringBuilder();
            if ((line = reader.readLine()) != null){
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <T> String spliceUrlAndParams(String baseUrl, T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuilder result = new StringBuilder(baseUrl).append("?");
        for (Field f: fields){
            if (Modifier.isPrivate(f.getModifiers())){
                f.setAccessible(true);
                try {
                    result.append(f.getName()).append("=").append(f.get(t)).append("&");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
