package com.lwl.bishe.service;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.constant.Constant;
import com.lwl.bishe.dao.mapper.KeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * date  2018/3/18
 * author liuwillow
 **/
@Service
public class KeyServiceImpl implements KeyService {
    private Queue<Key> keyQueue = new ConcurrentLinkedQueue<>();
    private ThreadLocal<Key> localKey = new ThreadLocal<>();
    private static final ThreadLocal<Integer> keyTimes = new ThreadLocal<>();

    @Autowired
    private KeyMapper keyMapper;

    @Override
    public boolean initKeys() {
       return keyMapper.initKeys() > 0;
    }

    @Override
    public Key getUsefulKey() {
        assertKeyNullPoint();
        //每调用2000次更新数据库times值
        if (getKeyTimes() % Constant.REQUEST_TIMES_PER_GAP == 0 && getKeyTimes() != 0){
            keyMapper.updateTimesByName(localKey.get().getName(), getKeyTimes());
        }
        //如果大于29000次，换个key
        if (getKeyTimes() > Constant.REQUEST_TIMES_LIMIT){
            localKey.set(keyQueue.poll());
            keyTimes.set(0);
        }
        return localKey.get();
    }

    @Override
    public void keyTimesAutoIncrement() {
        assertKeyTimesNullPointer();
        keyTimes.set(keyTimes.get() + 1);
    }

    private void assertKeyNullPoint() {
        if (keyQueue.isEmpty()){
            keyQueue.addAll(keyMapper.listAllKeys());
        }
        if (localKey.get() == null){
            localKey.set(keyQueue.poll());
        }
    }

    private int getKeyTimes() {
        assertKeyTimesNullPointer();
        return keyTimes.get();
    }

    private void assertKeyTimesNullPointer() {
        if (ObjectUtils.isEmpty(keyTimes.get())){
            keyTimes.set(0);
        }
    }

}