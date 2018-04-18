package com.lwl.bishe.util;

import com.lwl.bishe.controller.dto.PolygonDTO;
import org.springframework.util.StringUtils;

/**
 * date  2018/4/14
 * author liuwillow
 **/
public class ParamsUtil {
    private ParamsUtil(){}

    public static boolean isPolygonDTOEmpty(PolygonDTO polygonDTO) {
        return polygonDTO.getPage() == null || StringUtils.isEmpty(polygonDTO.getPolygon())
                || StringUtils.isEmpty(polygonDTO.getType());
    }
}

