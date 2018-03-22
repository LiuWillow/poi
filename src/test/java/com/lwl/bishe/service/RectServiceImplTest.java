package com.lwl.bishe.service;

import com.lwl.bishe.App;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.constant.RectConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * date  2018/3/18
 * author liuwillow
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RectServiceImplTest {
    @Autowired
    private RectService rectService;
    @Test
    public void initRects() throws Exception {
        rectService.initRects();
    }

    @Test
    public void saveRect() {
        Rect rectLeft = new Rect(RectConstant.BORDER_POINT_1, RectConstant.BORDER_POINT_2,
                RectConstant.BORDER_POINT_5, RectConstant.BORDER_POINT_6);
        assert rectService.saveRect(rectLeft);
    }


    @Test
    public void clearRects() {
        rectService.clearRects();
    }

}