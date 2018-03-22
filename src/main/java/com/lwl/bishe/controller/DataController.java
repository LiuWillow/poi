package com.lwl.bishe.controller;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Location;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.bean.Response;
import com.lwl.bishe.constant.Constant;
import com.lwl.bishe.constant.RectConstant;
import com.lwl.bishe.service.GaodeService;
import com.lwl.bishe.service.KeyService;
import com.lwl.bishe.service.RectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * date  2018/3/18
 * author liuwillow
 **/
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private KeyService keyService;

    @Autowired
    private RectService rectService;


    @PutMapping("/key/init")
    public Response initKeys() {
        Response response = new Response();
        if (keyService.initKeys()) {
            response.setMsg("ok");
            return response;
        }
        response.setMsg("error");
        return response;
    }

    @GetMapping("/rect/split")
    public Response test() {
        rectService.initRects();
        return new Response("success");
    }

    @GetMapping("/poi")
    public Response poi(){
        //TODO 和这里
        return new Response("ok");
    }

}
