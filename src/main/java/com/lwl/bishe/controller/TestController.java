package com.lwl.bishe.controller;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Response;
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

    @GetMapping("/oh")
    public Object getObject(){
        StringBuilder builder = new StringBuilder();
        builder.append("        ****     ****\r\n");
        builder.append("      *******  *******\r\n");
        builder.append("     *******************\r\n");
        builder.append("       ***************\r\n");
        builder.append("         ***********\r\n");
        builder.append("           *******\r\n");
        builder.append("             ***\r\n");
        return builder.toString();
    }

    @GetMapping("/test")
    public String wangzhan(){
        StringBuilder builder = new StringBuilder();
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        builder.append("马行广傻逼|");
        return builder.toString();
    }
}
