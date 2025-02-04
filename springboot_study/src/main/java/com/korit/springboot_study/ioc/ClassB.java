package com.korit.springboot_study.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component // Spring Bean으로 등록해서 IoC 컨테이너가 관리
// ClassB는 Spring이 자동으로 객체를 생성하고 관리하는 클래스가 됨.
// @Component로 등록된 빈은 기본적으로 애플리케이션 실행 중에 변경되지 않는다
public class ClassB {

    @Qualifier("c1")
    // 변수명을 지정 (ClassC 타입의 Bean 중에서 "c1"을 선택)
    // 동일 타입의 빈이 여러 개 있을 때 주입할 대상을 명시적으로 지정하지 않으면 오류가 발생
    @Autowired()
    // 자동으로 객체를 주입, required = true가 기본값
    // Spring은 주입할 객체를 찾을 때 타입을 기준으로 맞는 빈을 찾고, 찾은 빈을 해당 필드나 생성자에 주입
    private ClassC c1;

    @Qualifier("c2")
    @Autowired()
    private ClassC c2;

    public void classCallB() {
        System.out.println("ClassB 메서드 호출");
    }
}
/*
이 코드의 동작 방식
Spring이 ClassB 객체를 생성 (@Component 때문)
ClassC 타입의 Bean 중에서 "c1"과 "c2" 라는 이름을 가진 Bean을 찾아 c1, c2 변수에 주입 (@Autowired, @Qualifier)
classCallB() 메서드를 호출하면 "ClassB 호출"이 출력됨.
 */