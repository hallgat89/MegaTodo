package com.github.hallgat89.megatodo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Aspect
@Component
public class PageLoggingAspect {

    Logger logger = LogManager.getLogger(PageLoggingAspect.class);

    @Before("execution(* com.github.hallgat89.megatodo.controller..*(..))")
    public void logCall(JoinPoint point) throws Throwable {
        String name=point.getSignature().getName();
        logger.info(name+" was called. (WebRequest)");
    }
}
