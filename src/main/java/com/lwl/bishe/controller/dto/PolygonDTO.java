package com.lwl.bishe.controller.dto;

/**
 * date  2018/4/14
 * author liuwillow
 **/
public class PolygonDTO {
    private String polygon;
    private String type;
    private Integer page;

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
