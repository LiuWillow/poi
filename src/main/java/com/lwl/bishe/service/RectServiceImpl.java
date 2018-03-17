package com.lwl.bishe.service;

import com.lwl.bishe.bean.Location;
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
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int BELOW = 2;
    private static final int LEFT = 3;
    @Autowired
    private SplitService splitService;

    @Override
    public void initRects() {
        Rect rectLeft = new Rect(RectConstant.BORDER_POINT_1, RectConstant.BORDER_POINT_2,
                RectConstant.BORDER_POINT_5, RectConstant.BORDER_POINT_6);
        Location[] upLocations = splitLine(rectLeft, UP);
        Location[] rightLocations = splitLine(rectLeft, RIGHT);
        Location[] belowLocations = splitLine(rectLeft, BELOW);
        Location[] leftLocations = splitLine(rectLeft, LEFT);
        for (int i = 0; i < RectConstant.SPLIT_NUM_OF_BORDER; i++){
            
        }
        splitService.split(rectLeft, rect -> true);
    }

    private Location[] splitLine(Rect rect, int type) {
        Location[] locations = new Location[RectConstant.SPLIT_NUM_OF_BORDER + 1];
        Location lo1 = getLo1ByType(rect, type);
        Location lo2 = getLo2ByType(rect, type);

        double gapLng = lo1.getLng() - lo2.getLng() / RectConstant.SPLIT_NUM_OF_BORDER;
        double gapLat = lo1.getLat() - lo2.getLat() / RectConstant.SPLIT_NUM_OF_BORDER;
        int i = 0;
        while (i++ <= RectConstant.SPLIT_NUM_OF_BORDER){
            double newLng = lo1.getLng() + gapLng * i;
            double newLat = lo1.getLat() + gapLat * i;
            Location location = new Location(newLng, newLat);
            locations[i] = location;
        }
        return locations;
    }

    private Location getLo2ByType(Rect rect, int type) {
        switch (type){
            case UP:
                return rect.getLo2();
            case RIGHT:
                return rect.getLo3();
            case BELOW:
                return rect.getLo3();
            case LEFT:
                return rect.getLo4();
        }
        return null;
    }

    private Location getLo1ByType(Rect rect, int type) {
        switch (type){
            case UP:
                return rect.getLo1();
            case RIGHT:
                return rect.getLo2();
            case BELOW:
                return rect.getLo4();
            case LEFT:
                return rect.getLo1();
        }
        return null;
    }
}
