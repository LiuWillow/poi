package com.lwl.bishe.service;

import com.lwl.bishe.App;
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
public class CacheServiceImplTest {
    @Autowired
    private CacheService cacheService;
    @Test
    public void setDepth() throws Exception {
        Thread thread1 = new Thread(() -> {
            assertEquals(0, cacheService.getTimes());
            cacheService.setTimes(15);
            assertEquals(15, cacheService.getTimes());
            cacheService.timesAutoIncrement();
            assertEquals(16, cacheService.getTimes());
        });

        Thread thread2 = new Thread(() -> {
            assertEquals(0, cacheService.getTimes());
            cacheService.setTimes(11);
            assertEquals(11, cacheService.getTimes());
            cacheService.timesAutoIncrement();
            assertEquals(12, cacheService.getTimes());
        });

        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }

}