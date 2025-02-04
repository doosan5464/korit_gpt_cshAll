package com.korit.springboot_study.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

    @Autowired
    private ClassD d;
    // 지금 ClassA 는 Bean으로 부품인데 ClassD에 의존성이 있어 실행이 안되지만?
    // ConfigA에서 ClassD를 @Bean으로 만들어서 실행되기때문에(Configuration의 특징) ClassA의 의존성도 해결
    // 한마디로 다른 클래스를 bean으로 등록하여 @Autowired를 하면 자동으로 주입받아서 사용 가능하다

    public void classCallA() {
        System.out.println("ClassA 메서드 호출");
    }
}
