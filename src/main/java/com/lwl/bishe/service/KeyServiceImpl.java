package com.lwl.bishe.service;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.constant.Constant;
import com.lwl.bishe.dao.mapper.KeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * date  2018/3/18
 * author liuwillow
 **/
@Service
public class KeyServiceImpl implements KeyService {
    private int index = 0;
    private int i = 0;
    private List<Key> keyList = new ArrayList<>();

    @Autowired
    private KeyMapper keyMapper;

    @Override
    public boolean initKeys() {
       return keyMapper.initKeys() > 0;
    }

    @Override
    public Key getUsefulKey() {
        if (keyList.isEmpty()){
            keyList = keyMapper.listAllKeys();
        }
        if (i > Constant.REQUEST_TIMES_LIMIT){
            i = 0;
            keyMapper.disableKey(keyList.get(index).getName());
            index++;
        }
        i++;
        return keyList.get(index);
    }
}
