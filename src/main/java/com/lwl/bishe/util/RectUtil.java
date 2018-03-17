package com.lwl.bishe.util;

import com.lwl.bishe.bean.Location;
import com.lwl.bishe.bean.Rect;

/**
 * date  2018/3/17
 * author liuwillow
 **/
public class RectUtil {
    private RectUtil(){}
    public static Location getMidOfLine(Location location1, Location location2){
        double avgLng = (location1.getLng() + location2.getLng()) / 2;
        double avgLat = (location1.getLat() + location2.getLat()) / 2;
        return new Location(avgLng, avgLat);
    }

    public static Location getCenterOfRect(Rect rect){
        Location midOfLine1 = getMidOfLine(rect.getLo1(), rect.getLo2());
        Location midOfLine2 = getMidOfLine(rect.getLo3(), rect.getLo4());
        return getMidOfLine(midOfLine1, midOfLine2);
    }
}