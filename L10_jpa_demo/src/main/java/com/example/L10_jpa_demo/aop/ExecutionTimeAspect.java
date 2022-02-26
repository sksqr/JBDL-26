package com.example.L10_jpa_demo.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {


    private static Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Around("execution(* com.example.L10_jpa_demo.controllers.PersonController.*(..)) && @annotation(com.example.L10_jpa_demo.aop.LogExecutionTime)")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.error("className={}, methodName={}, arguments={},  timeMs={}", new Object[]{((MethodSignature)MethodSignature.class.cast(point.getSignature())).getDeclaringTypeName(), ((MethodSignature)MethodSignature.class.cast(point.getSignature())).getMethod().getName(), point.getArgs(), executionTime});
        return result;
    }


    @Before("execution(* com.example.L10_jpa_demo.controllers.PersonController.*(..))")
    public void beforeMethod(){
        logger.error("Executing before method advice");
    }

    @After("execution(* com.example.L10_jpa_demo.controllers.PersonController.*(..))")
    public void afterMethod(){
        logger.error("Executing before method advice");
    }


}












//@Around("execution(com.example.L10_jpa_demo.controllers * *) && @annotation(org.springframework.web.bind.annotation.GetMapping)")