package com.lwl.bishe.service;

import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.constant.RectConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * date  2018/3/17
 * author liuwillow
 **/
@Service
public class RectServiceImpl implements RectService {
    @Autowired
    private SplitService splitService;

    public void initRects() {
        Rect rectLeft = new Rect(RectConstant.BORDER_POINT_1, RectConstant.BORDER_POINT_2,
                RectConstant.BORDER_POINT_5,RectConstant.BORDER_POINT_6);
        splitService.split(rectLeft, rect -> true);
    }
}
