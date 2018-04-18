package com.lwl.bishe.aop;

import com.lwl.bishe.service.KeyService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * date  2018/3/23
 * author liuwillow
 **/
@Aspect
@Component
public class KeyTimesAspect {
    @Autowired
    private KeyService keyService;

    @Pointcut("execution(* com.lwl.bishe.service.HttpService.sendGet(..))")
    public void send(){}

    @Around("send()")
    public String autoPlus(ProceedingJoinPoint joinPoint) throws Throwable {
        String result = (String) joinPoint.proceed();
        System.out.println(result);
        keyService.keyTimesAutoIncrement();
        return result;
    }
}
