package com.lwl.bishe.service;

import com.lwl.bishe.bean.GaodeResponse;
import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.service.mongodb.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * date  2018/3/22
 * author liuwillow
 **/
@Service
public class PoiServiceImpl implements PoiService {
    private static ThreadLocal<Integer> rectNum = new ThreadLocal<>();
    @Autowired
    private GaodeService gaodeService;

    @Autowired
    private KeyService keyService;

    @Autowired
    private RectService rectService;

    @Autowired
    private MongoService mongoService;

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 15;
    private static final int KEEP_ALIVE_TIME = 1000;
    private static final int DEFAULT_PAGE_SIZE = 1000;


    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

    @Override
    public boolean searchPoisAndSave() {

        for (int i = 0; i < CORE_POOL_SIZE; i++){
            doProcess();
        }
        return false;
    }

    private void doProcess() {
        Runnable task =() -> {
            if (rectNum.get() == null){
                rectNum.set(0);
            }
            while (true){
                List<Rect> list =  rectService.listAndUpdateEnabledRects(1, DEFAULT_PAGE_SIZE);
                if (list.size() == 0){
                    break;
                }
                list.forEach(rect -> {
                    Key key = keyService.getUsefulKey();
                    List<GaodeResponse> gaodeResponseList =
                            gaodeService.getGaodeResponseByRect(rect, key);
                    mongoService.savePois(gaodeResponseList);
                    rectNum.set(rectNum.get() + 1);
                    System.out.println(Thread.currentThread().getName() + rectNum.get());
                });
            }
        };
        executor.execute(task);
    }


}
