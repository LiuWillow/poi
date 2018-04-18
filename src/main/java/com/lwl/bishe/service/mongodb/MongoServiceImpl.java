package com.lwl.bishe.service.mongodb;

import com.lwl.bishe.bean.GaodeResponse;
import com.lwl.bishe.bean.Page;
import com.lwl.bishe.bean.Poi;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date  2018/4/5
 * author liuwillow
 **/
@Service
public class MongoServiceImpl implements MongoService {
    private static final String POI_COLLECTION = "poi";
    private static final int DEFAULT_PAGE_SIZE = 25;

    //以下缓存考虑到count的效率
    private static final String MAX_COUNT_KEY = "max_count";
    private static final String POSITION_KEY = "position";
    private static final String TYPE_KEY = "type";
    private Map<String, Long> maxCountCache = new HashMap<>();
    private Map<String, List<Position>> positionCache = new HashMap<>();
    private Map<String, String> typeCache = new HashMap<>();

    @Autowired
    private MongoDatabase db;

    @Override
    public boolean savePois(List<GaodeResponse> gaodeResponseList) {
        if (db.getCollection(POI_COLLECTION) == null){
            db.createCollection(POI_COLLECTION);
        }
        MongoCollection<Document> collection = db.getCollection(POI_COLLECTION);
        List<Document> documentList = new ArrayList<>(gaodeResponseList.size());
        gaodeResponseList.forEach(gaodeResponse -> {
            List<Poi> poiList = gaodeResponse.getPois();
            poiList.forEach(poi -> {
                if (poi.getLocation() != null){
                    Document document = getDocument(poi);
                    documentList.add(document);
                }
            });
        });
        collection.insertMany(documentList);
        return true;
    }

    @Override
    public Page<Poi> searchPoisWithinPolygon(List<Position> pointList, int page, String type) {
        if (db.getCollection(POI_COLLECTION) == null){
            db.createCollection(POI_COLLECTION);
        }
        MongoCollection<Document> collection = db.getCollection(POI_COLLECTION);
        Bson locationBson = Filters.geoWithin(Poi.LOCATION, new Polygon(pointList));
        Bson typeBson = Filters.text(type);
        boolean isSame = isMaxCountRequestSame(pointList, type);
        return pagingSearch(locationBson, typeBson, collection, page, isSame);
    }

    private boolean isMaxCountRequestSame(List<Position> pointList, String type) {
        if (positionCache.get(POSITION_KEY) == null || typeCache.get(TYPE_KEY) == null
                || maxCountCache.get(MAX_COUNT_KEY) == null){
            positionCache.put(POSITION_KEY, pointList);
            typeCache.put(TYPE_KEY, type);
            return false;
        }
        if (!isSame(pointList, positionCache.get(POSITION_KEY))
                || !type.equals(typeCache.get(TYPE_KEY))){
            positionCache.put(POSITION_KEY, pointList);
            typeCache.put(TYPE_KEY, type);
            return false;
        }

        return true;
    }

    private boolean isSame(List<Position> pointList, List<Position> positionList) {
        int size1 = pointList.size();
        int size2 = positionList.size();
        if (size1 != size2){
            return false;
        }
        for (int i = 0; i < size1; i++){
            if (!pointList.get(i).equals(positionList.get(i))){
                return false;
            }
        }
        return true;
    }

    private Page<Poi> pagingSearch(Bson locationBson, Bson typeBson,
                                   MongoCollection<Document> collection, int page, boolean isSame){
        List<Poi> list = new ArrayList<>();
        Block<Document> addToPoiList = getBlock(list);
        long maxCount;
        if (isSame){
            maxCount = maxCountCache.get(MAX_COUNT_KEY);
        }else{
            maxCount = collection.count(locationBson);
            maxCountCache.put(MAX_COUNT_KEY, maxCount);
        }

        collection.find(locationBson)
                .filter(typeBson)
                .skip(DEFAULT_PAGE_SIZE * (page - 1))
                .limit(DEFAULT_PAGE_SIZE)
                .forEach(addToPoiList);

        return getPageByElements(page, list, maxCount);
    }

    private Block<Document> getBlock(List<Poi> list) {
        return document -> {
            Poi poi = new Poi();
            poi.setPname((String) document.get(Poi.P_NAME));
            poi.setName((String) document.get(Poi.NAME));
            poi.setCityname((String) document.get(Poi.CITY_NAME));
            poi.setCityname((String) document.get(Poi.CITY_NAME));
            poi.setType((String) document.get(Poi.TYPE));
            List<Double> location = (List<Double>) ((Document) document.get(Poi.LOCATION)).get("coordinates");
            poi.setLocation(location.get(0) + "," + location.get(1));
            poi.setAdname((String) document.get(Poi.AD_NAME));
            list.add(poi);
        };
    }

    private Page<Poi> getPageByElements(int page, List<Poi> list, long maxCount) {
        Page<Poi> result = new Page<>();
        result.setCurrentPage(page);
        result.setList(list);
        result.setCurrentCount(list.size());
        int maxPage = (int) Math.ceil(maxCount / (DEFAULT_PAGE_SIZE * 1.0));
        result.setMaxPage(maxPage);
        return result;
    }
    private Document getDocument(Poi poi) {
        Document document = new Document()
                .append(Poi.AD_NAME, poi.getAdname())
                .append(Poi.CITY_NAME, poi.getCityname())
                .append(Poi.NAME, poi.getName())
                .append(Poi.P_NAME, poi.getPname())
                .append(Poi.TYPE, poi.getType())
                .append(Poi.TYPE_CODE, poi.getTypecode());
        String locationString = poi.getLocation();
        String[] locationArray = locationString.split(",");
        double[] location = new double[]{
                Double.valueOf(locationArray[0]), Double.valueOf(locationArray[1])};
        Point point = new Point(new Position(location[0], location[1]));
        document.append(Poi.LOCATION, point);
        return document;
    }

}
