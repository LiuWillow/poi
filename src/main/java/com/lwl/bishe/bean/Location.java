package com.lwl.bishe.bean;
/**
 * date  2018/3/17
 * author liuwillow
 **/
public class Location {
    //经度
    private double lng;
    //纬度
    private double lat;

    public Location() { }

    public Location(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    public String toParam() {
        return lng + "," + lat;
    }
}
