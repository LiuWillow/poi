package com.lwl.bishe.service;

import com.lwl.bishe.bean.Rect;

/**
 * date  2018/3/17
 * author liuwillow
 **/
public interface RectService {
    void initRects();
    boolean saveRect(Rect rect);
    boolean clearRects();
    void test();
}
