package com.lwl.bishe.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * date  2018/4/17
 * author liuwillow
 **/
@Service
public class HttpServiceImpl implements HttpService {
    @Override
    public <T> String sendGet(String baseUrl, T t) {
        String finalUrl = spliceUrlAndParams(baseUrl, t);
        URL url;
        HttpURLConnection connection;
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
