package com.korit.springboot_study.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j // lombok에서 그냥 Logger를 들고와준다?
public class LoggingAspect {

    @Pointcut("execution(* com.korit.springboot_study.controller.advice.*.*(..))")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Exception exception = (Exception) joinPoint.getArgs()[0];
        log.error(exception.getMessage());
        return joinPoint.proceed();
    }
}
