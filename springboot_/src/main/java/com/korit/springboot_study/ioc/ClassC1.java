package com.korit.springboot_study.ioc;

import org.springframework.stereotype.Component;

@Component(value = "c1") // IoC 컨테이너에 의해 관리되는 하나의 Bean. 다른 구현체와 구분하려고
public class ClassC1 implements ClassC{

    @Override
    public void classCallC() {
        System.out.println("ClassC1 호출");
    }
}
