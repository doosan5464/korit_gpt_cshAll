package com.korit.servlet_study.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // ElementType.~~ : ~~ 위에 달 수 있는 annotation (지금은 메서드 위에만)
@Retention(RetentionPolicy.RUNTIME) // RetentionPolicy.~~ : annotation을 언제 적용할 건지 (지금은 런타임때)
public @interface JwtValid {
}
