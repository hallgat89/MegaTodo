package com.github.hallgat89.megatodo.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ApiLoggingAspect {

    Logger logger = LogManager.getLogger(ApiLoggingAspect.class);

    @Before("execution(* com.github.hallgat89.megatodo.api..*(..))")
    public void logCall(JoinPoint point) throws Throwable {
        String name = point.getSignature().getName();
        logger.info(name + " was called. (API request)");
    }
}
