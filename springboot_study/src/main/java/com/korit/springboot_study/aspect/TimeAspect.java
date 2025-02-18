package com.korit.springboot_study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeAspect {


    @Pointcut("execution(* com.korit.springboot_study.service.PostService.*(..))")
    private void executionPointCut() {}

    // 해당위치가 @Pointcut으로 되게끔
    @Pointcut("@annotation(com.korit.springboot_study.aspect.annotation.TimeAop)")
    private void annotationPointCut() {}

    @Around("executionPointCut()||annotationPointCut()")
    // 메소드 실행시간 얼마 걸렸는지 로직
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        System.out.println("메소드 실행시간: " + stopWatch.getTotalTimeSeconds());
        return result;
    }
}