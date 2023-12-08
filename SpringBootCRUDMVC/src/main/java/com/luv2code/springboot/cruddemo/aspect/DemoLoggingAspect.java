package com.luv2code.springboot.cruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setting up pointcut declaration
    @Pointcut("execution (* com.luv2code.springboot.cruddemo.controller.*.* (..))")
    private void forControllerPackage () {}

    @Pointcut("execution (* com.luv2code.springboot.cruddemo.service.*.* (..))")
    private void forServicePackage () {}

    @Pointcut("execution (* com.luv2code.springboot.cruddemo.dao.*.* (..))")
    private void forDAOPackage () {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("====> in @Before: calling method " + method);

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            myLogger.info("Arguments are ----------- " + tempArg);
        }

    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint thejoinPoint,Object result) {
        String method = thejoinPoint.getSignature().toShortString();
        myLogger.info("====> in @Before: calling method " + method);

        myLogger.info("====> result:  " + result);
    }


}
