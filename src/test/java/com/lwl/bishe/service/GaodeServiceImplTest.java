package com.lwl.bishe.service;

import com.lwl.bishe.App;
import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.dao.mapper.RectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * date  2018/3/18
 *
 * author liuwillow
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class GaodeServiceImplTest {

    @Autowired
    private GaodeService gaodeService;

    @Autowired
    private RectMapper rectMapper;

    @Autowired
    private CacheService cacheService;

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            10, 3000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>());

    @Test
    public void getCountByRect(){
        Key key = new Key();
        key.setName("47ce0075cba33a14c797298bc90e303d");

        int amount = rectMapper.countRects();
        System.out.println("rect个数为" + amount);
        for (int begin = 1; begin < amount; begin += 1000) {
            List<Rect> rectList = rectMapper.listRect(begin, 1000);
            System.out.println("begin:" + begin);
            rectList.forEach(rect -> {
                int count = gaodeService.getCountByRect(rect, key);
                cacheService.addTimes(count);
            });
        }
        System.out.println("poi总数为" + cacheService.getTimes());
    }
}