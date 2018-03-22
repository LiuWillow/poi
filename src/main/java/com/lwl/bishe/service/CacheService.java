package com.lwl.bishe.service;

/**
 * date  2018/3/18
 * author liuwillow
 **/
public interface CacheService {
    void setTimes(int times);
    void addTimes(int times);
    int getTimes();
    void timesAutoIncrement();
}
