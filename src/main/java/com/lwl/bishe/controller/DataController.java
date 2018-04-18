package com.lwl.bishe.controller;

import com.lwl.bishe.bean.*;
import com.lwl.bishe.service.KeyService;
import com.lwl.bishe.service.PoiService;
import com.lwl.bishe.service.RectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private PoiService poiService;

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
    public Response splitRects() {
        rectService.splitRects();
        return new Response("success");
    }

    @GetMapping("/rect/init")
    public Response initRects() {
        if (rectService.updateAllRectsEnabled()){
            return new Response("success");
        }
        return new Response("error");
    }

    @GetMapping("/rect/clear")
    public Response clearRects() {
        rectService.clearRects();
        return new Response("success");
    }

    @GetMapping("/poi/save")
    public Response savePois(){
        poiService.searchPoisAndSave();
        return new Response("ok");
    }

}
