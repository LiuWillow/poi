package com.lwl.bishe.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * date  2018/4/17
 * author liuwillow
 **/
@Aspect
@Component
public class RectSplitAspect {
    private int i = 0;
    @Pointcut("execution(* com.lwl.bishe.dao.mapper.RectMapper.saveRect(..))")
    public void save(){}

    @Around("save()")
    public int printRectSplitedNum(ProceedingJoinPoint joinPoint) throws Throwable {
        int result = (int) joinPoint.proceed();
        System.out.println("保存了第" + ++i + "个矩形" + LocalDateTime.now());
        return result;
    }
}
