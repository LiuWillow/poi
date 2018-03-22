package com.lwl.bishe.service;

import com.lwl.bishe.bean.Key;
import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.constant.Constant;
import com.lwl.bishe.constant.RectConstant;
import com.lwl.bishe.dao.mapper.RectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * date  2018/3/17
 * author liuwillow
 **/
@Service
public class RectServiceImpl implements RectService {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            10, 3000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>());

    @Autowired
    private SplitService splitService;

    @Autowired
    private KeyService keyService;

    @Autowired
    private GaodeService gaodeService;

    @Autowired
    private RectMapper rectMapper;

    @Override
    public void initRects() {
        clearRects();
        Rect rectLeft = new Rect(RectConstant.BORDER_POINT_1, RectConstant.BORDER_POINT_2,
                RectConstant.BORDER_POINT_5, RectConstant.BORDER_POINT_6);
        splitService.split(rectLeft);

        Rect rightLeft = new Rect(RectConstant.BORDER_POINT_2, RectConstant.BORDER_POINT_3,
                RectConstant.BORDER_POINT_4, RectConstant.BORDER_POINT_5);
        splitService.split(rightLeft);
    }

    @Override
    public boolean saveRect(Rect rect) {
        return rectMapper.saveRect(rect) > 0;
    }

    @Override
    public boolean clearRects() {
        rectMapper.clearRects();
        return true;
    }

    @Override
    public void test() {
        if (executor.getQueue().isEmpty()){
            System.out.println("线程池中无任务");
        }
        Runnable task1 = this::sleep;
        Runnable task2 = this::sleep;
        Runnable task3 = this::sleep;
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.shutdown();
        while (true){
            if (executor.isTerminated()){
                System.out.println("线程池中任务执行完毕");
                break;
            }
        }
    }

    private void sleep() {
        try {
            System.out.println(Thread.currentThread().getName() + ":正在执行");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
