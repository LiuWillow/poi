package com.lwl.bishe.bean;

import org.springframework.util.StringUtils;

import static ch.qos.logback.core.CoreConstants.EMPTY_STRING;

/**
 * date  2018/3/17
 * author liuwillow
 **/
public class GaodeRequest {
    public static final int DEFAULT_OFFSET = 25;
    public static final int DEFAULT_PAGE = 1;
    public static final String ALL_INFO = "all";
    public static final String BASE_INFO = "base";
    private String key;
    private String polygon;
    private String keywords;
    private String types;
    private Integer offset;
    private Integer page;
    private String extensions;

    public GaodeRequest() {
    }

    public GaodeRequest(GaodeBuilder gaodeBuilder){
        this.key = gaodeBuilder.key;
        this.polygon = gaodeBuilder.polygon;
        this.keywords = gaodeBuilder.keywords;
        this.types = gaodeBuilder.types;
        this.offset = gaodeBuilder.offset;
        this.page = gaodeBuilder.page;
        this.extensions = gaodeBuilder.extensions;
    }

    public static GaodeBuilder newBuilder(){
        return new GaodeBuilder();
    }

    public static class GaodeBuilder{
        private String key;
        private String polygon;
        private String keywords;
        private String types;
        private Integer offset;
        private Integer page;
        private String extensions;

        public GaodeBuilder key(String key){
            this.key = key;
            return this;
        }

        public GaodeBuilder polygon(String polygon){
            this.polygon = polygon;
            return this;
        }

        public GaodeBuilder keywords(String keywords){
            this.keywords = keywords;
            return this;
        }

        public GaodeBuilder types(String types){
            this.types = types;
            return this;
        }

        public GaodeBuilder offset(Integer offset){
            this.offset = offset;
            return this;
        }

        public GaodeBuilder page(Integer page){
            this.page = page;
            return this;
        }

        public GaodeBuilder extensions(String extensions){
            this.extensions = extensions;
            return this;
        }

        public GaodeRequest build(){
            if (StringUtils.isEmpty(this.keywords)){
                this.keywords = EMPTY_STRING;
            }
            if (StringUtils.isEmpty(this.types)){
                this.types = EMPTY_STRING;
            }
            if (StringUtils.isEmpty(this.extensions)){
                this.extensions = ALL_INFO;
            }
            if (this.offset == null){
                this.offset = DEFAULT_OFFSET;
            }
            if (this.page == null){
                this.page = DEFAULT_PAGE;
            }
            return new GaodeRequest(this);
        }

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }
}
