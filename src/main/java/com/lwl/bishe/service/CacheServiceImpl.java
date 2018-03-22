package com.lwl.bishe.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * date  2018/3/18
 * author liuwillow
 **/
@Service
public class CacheServiceImpl implements CacheService {
    private static final ThreadLocal<Integer> times = new ThreadLocal<>();

    @Override
    public void setTimes(int deep) {
        times.set(deep);
    }

    @Override
    public void addTimes(int deep) {
        assertNullPointer();
        times.set(times.get() + deep);
    }

    @Override
    public int getTimes() {
        assertNullPointer();
        return times.get();
    }

    @Override
    public void timesAutoIncrement() {
        assertNullPointer();
        times.set(times.get() + 1);
    }

    private void assertNullPointer() {
        if (ObjectUtils.isEmpty(times.get())){
            times.set(0);
        }
    }
}
