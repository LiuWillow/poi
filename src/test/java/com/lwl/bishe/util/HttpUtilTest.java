package com.lwl.bishe.util;

import com.lwl.bishe.bean.GaodeRequest;
import com.lwl.bishe.constant.Constant;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * date  2018/3/17
 * author liuwillow
 **/
public class HttpUtilTest {
    @Test
    public void sendGet() throws Exception {
        GaodeRequest gaodeRequest = GaodeRequest.newBuilder()
                .key("1230aabd2f6c3e96d02708961a07c88e")
                .polygon("116.460988,40.006919;116.48231,40.007381;" +
                        "116.47516,39.99713;116.472596,39.985227;" +
                        "116.45669,39.984989;116.460988,40.006919")
                .build();

        String result = HttpUtil.sendGet(Constant.BASE_URL, gaodeRequest);
        System.out.println(result);
    }

    @Test
    public void baoliu() {
        double a = 3.1234567;
        BigDecimal b = new BigDecimal(a);
        double x = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(x);
    }
}