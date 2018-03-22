package com.lwl.bishe.service;

import com.lwl.bishe.bean.GaodeResponse;
import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Rect;

import java.util.List;

/**
 * date  2018/3/18
 * author liuwillow
 **/
public interface GaodeService {
    int getCountByRect(Rect rect, Key key);
    List<GaodeResponse> getGaodeResponseByRect(Rect rect, Key key);
}
