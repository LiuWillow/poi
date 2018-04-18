package com.lwl.bishe.controller;

import com.lwl.bishe.bean.Page;
import com.lwl.bishe.bean.Poi;
import com.lwl.bishe.bean.Response;
import com.lwl.bishe.controller.dto.PolygonDTO;
import com.lwl.bishe.service.mongodb.MongoService;
import com.lwl.bishe.util.ParamsUtil;
import com.mongodb.client.model.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * date  2018/4/3
 * author liuwillow
 **/
@RestController
@RequestMapping("/poi")
public class PoiController {
    @Autowired
    private MongoService mongoService;
    /**
     * 分页获取某个范围内的Poi，请求时传入多边形顶点，并且头尾应闭合，用|分隔
     * @param polygonDTO 请求参数，包括polygon，page，type
     * */
    @GetMapping(value = "/polygon")
    public Response polygon(PolygonDTO polygonDTO){
        if (ParamsUtil.isPolygonDTOEmpty(polygonDTO)){
            Response response = new Response();
            response.setMsg("430");
            return response;
        }
        List<Position> positionList = analysisPolygonString(polygonDTO.getPolygon());

        Page<Poi> poiPage = mongoService.searchPoisWithinPolygon(positionList, polygonDTO.getPage(), polygonDTO.getType());
        Response response = new Response();
        response.setMsg("ok");
        response.setData(poiPage);
        return response;
    }



    private List<Position> analysisPolygonString(String polygon) {
        String[] polygons = polygon.split("\\|");
        int length = polygons.length;
        List<Position> positionList = new ArrayList<>(length);
        for (int i = 0; i < length; i++){
            String[] lnglat = polygons[i].split(",");
            Position position = new Position(Double.valueOf(lnglat[0]),
                    Double.valueOf(lnglat[1]));
            positionList.add(position);
        }
        return positionList;
    }
}
