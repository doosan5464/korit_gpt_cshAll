package com.korit.springboot_study.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect // AOP 기능을 수행하는 클래스라는 표시
@Component // 스프링이 자동으로 빈으로 등록
public class PrintParamsAspect {
    // 매번 sout로 확인했는데 그거 대신에 확인하는 용도

    private static final Logger log = LoggerFactory.getLogger(PrintParamsAspect.class);

    // @PointCut : 어떤 메서드를 가로챌지 정함
    @Pointcut("@annotation(com.korit.springboot_study.aspect.annotation.PrintParamsAop)")
    private void pointCut() {}

    @Around("pointCut()")
    // @Around : 메서드 실행 전후로 특정 작업을 수행
    // 로그 출력 로직
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        log.error("로그를 출력합니다.");

        for (int i = 0; i < parameterNames.length; i++) {
            System.out.println(parameterNames[i] + ": " + args[i]);
        }

        Object result = joinPoint.proceed();

        return result;
    }
}
