package com.lwl.bishe.service;

import com.lwl.bishe.bean.Rect;

import java.util.function.Predicate;


/**
 * date  2018/3/17
 * author liuwillow
 **/
public interface SplitService {
    void split(Rect rect, Predicate<Rect> predicate);
}
