package com.lwl.bishe.bean;

import java.util.List;

/**
 * date  2018/3/18
 * author liuwillow
 **/
public class GaodeResponse {
    public static final String COUNT = "count";
    private int status;
    private int count;
    private String info;
    private String infocode;
    private List<Poi> pois;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }
}
