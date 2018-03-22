package com.lwl.bishe.service;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Location;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.constant.Constant;
import com.lwl.bishe.dao.mapper.RectMapper;
import com.lwl.bishe.util.RectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * date  2018/3/17
 * author liuwillow
 **/
@Service
public class SplitServiceImpl implements SplitService {
    @Autowired
    private RectMapper rectMapper;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private KeyService keyService;

    @Autowired
    private GaodeService gaodeService;

    private int i = 0;

    @Override
    public void split(Rect rect) {
        Key key = keyService.getUsefulKey();
        int count = gaodeService.getCountByRect(rect, key);
        if (count < Constant.COUNT_LIMIT){
            if (count < 0){
                return;
            }
            System.out.println("第" + ++i + "个矩形" + "  " + LocalDateTime.now());
            cacheService.timesAutoIncrement();
            rectMapper.saveRect(rect);
            return;
        }

        Location center = RectUtil.getCenterOfRect(rect);
        Location up = RectUtil.getMidOfLine(rect.getLo1(), rect.getLo2());
        Location right = RectUtil.getMidOfLine(rect.getLo2(), rect.getLo3());
        Location below = RectUtil.getMidOfLine(rect.getLo3(), rect.getLo4());
        Location left = RectUtil.getMidOfLine(rect.getLo4(), rect.getLo1());

        split(new Rect(rect.getLo1(), up, center, left));
        split(new Rect(up, rect.getLo2(), right, center));
        split(new Rect(center, right, rect.getLo3(), below));
        split(new Rect(left, center, below, rect.getLo4()));
    }
}
