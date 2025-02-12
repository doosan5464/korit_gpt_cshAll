package com.korit.springboot_study.aspect.anntotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메서드에만 적용 가능
@Retention(RetentionPolicy.RUNTIME) // 실행 중에도 유지됨 (리플렉션으로 감지 가능)
public @interface PrintParamsAop {} // 기능 없음 (단순 마커 역할)
              // @PrintParamsAop 어노테이션을 붙이면 해당 메서드가 AOP 적용 대상이 됨
// 로그 출력 로직
