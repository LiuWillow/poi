package com.lwl.bishe.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lwl.bishe.bean.GaodeRequest;
import com.lwl.bishe.bean.GaodeResponse;
import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.constant.Constant;
import com.lwl.bishe.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * date  2018/3/18
 * author liuwillow
 **/
@Service
public class GaodeServiceImpl implements GaodeService {
    @Autowired
    private CacheService cacheService;

    @Override
    public int getCountByRect(Rect rect, Key key) {
        GaodeRequest requestParams = getRequest(rect, key);
        sleepForGapRequest();
        String resultString = HttpUtil.sendGet(Constant.BASE_URL, requestParams);
        return getCountFromResultString(resultString);
    }

    @Override
    public List<GaodeResponse> getGaodeResponseByRect(Rect rect, Key key) {

        GaodeRequest requestParams = getRequest(rect, key);
        String resultString = HttpUtil.sendGet(Constant.BASE_URL, requestParams);
        int count = getCountFromResultString(resultString);
        if (count < 0){
            return Collections.emptyList();
        }
        List<GaodeResponse> list = new ArrayList<>(Constant.DEFAULT_LIST_SIZE);
        GaodeResponse firstGaodeResponse = analysisRuesltString(resultString);
        list.add(firstGaodeResponse);

        int maxPage = (int) Math.ceil(count / GaodeRequest.DEFAULT_OFFSET);
        for (int p = 2; p <= maxPage; p++){
            //TODO zheli
//            GaodeRequest gaodeRequest = GaodeRequest.newBuilder()
//                    .key(key)
        }

        return null;
    }

    private GaodeResponse analysisRuesltString(String resultString) {
        return JSON.parseObject(resultString,
                new TypeReference<GaodeResponse>(){});
    }

    private GaodeRequest getRequest(Rect rect, Key key) {
        return GaodeRequest.newBuilder()
                .key(key.getName())
                .polygon(rect.toParam())
                .build();
    }
    private void sleepForGapRequest() {
        try {
            Thread.sleep(Constant.GAP_TIME_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getCountFromResultString(String resultString) {
        JSONObject jsonObject = JSON.parseObject(resultString);
        String countString = (String) jsonObject.get(GaodeResponse.COUNT);
        if (countString.isEmpty()){
            return -1;
        }
        return Integer.parseInt(countString);
    }

}
