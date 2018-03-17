package com.lwl.bishe.controller;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.dao.mapper.KeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
/**
 * date  2018/3/17
 * author liuwillow
 **/
@RestController
public class TestController {
    @Autowired
    private KeyMapper keyMapper;

    @GetMapping("/keys")
    public Key getKeys(){

        return keyMapper.listAllKeys().get(0);
    }
}
