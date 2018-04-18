package com.lwl.bishe.service.mongodb;

import com.lwl.bishe.bean.GaodeResponse;
import com.lwl.bishe.bean.Page;
import com.lwl.bishe.bean.Poi;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import java.util.List;

/**
 * date  2018/4/5
 * author liuwillow
 **/
public interface MongoService {
    boolean savePois(List<GaodeResponse> list);
    Page<Poi> searchPoisWithinPolygon(List<Position> pointList, int page, String type);
}
