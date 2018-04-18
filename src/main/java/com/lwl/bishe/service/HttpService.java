package com.lwl.bishe.service;

/**
 * date  2018/4/17
 * author liuwillow
 **/
public interface HttpService {
    <T> String sendGet(String baseUrl, T t);
}
